package com.asu.alliancebank.controllers.transactionmanagement;

import java.security.Principal;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asu.alliancebank.controllers.transactionmanagement.backingbean.OTPBackingBean;
import com.asu.alliancebank.controllers.transactionmanagement.backingbean.TransferFundsBackingBean;
import com.asu.alliancebank.domain.impl.Transaction;
import com.asu.alliancebank.domain.impl.TransferFunds;
import com.asu.alliancebank.factory.ITransferFundsFactory;
import com.asu.alliancebank.recaptcha.IReCaptchaManager;
import com.asu.alliancebank.security.otp.impl.OTPManager;
import com.asu.alliancebank.service.transaction.ITransferFundsManager;

@Controller
public class TransferFundsController {
	
	@Autowired
	private ITransferFundsFactory transferFundsFactory;
	
	@Autowired
	private ITransferFundsManager transferFundsManager;
	
	@Autowired
	private IReCaptchaManager reCaptchaManager;
	
	@Autowired
	private OTPManager otpManager;
	
	private static final Logger logger = LoggerFactory
			.getLogger(TransferFundsController.class);
	
	/**
	 * Helps create add {@link Transaction} form by getting the object ready for form jsp tags
	 * @param model ModelMap object for this request
	 * @param principal Transaction related details are access using this
	 * @return Prepares the Page for AddTransaction form
	 */
	@RequestMapping(value = "auth/trans/tranfunds", method = RequestMethod.GET)
	public String getToAddTransactionPage( ModelMap model, Principal principal) {
		// If user is authorized
		try {
			
			
			model.addAttribute("userNamesList", transferFundsManager.listAllUserNames(principal.getName()));
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		model.addAttribute("transferFundsBackingBean", new TransferFundsBackingBean(principal.getName()));
		return "auth/trans/tranfunds";
	}
	
	@RequestMapping(value = "auth/trans/tranfunds", method = RequestMethod.POST)
	public String transferfunds(HttpServletRequest req, @Valid @ModelAttribute TransferFundsBackingBean transferfundsForm, BindingResult result, ModelMap map, Principal principal) throws SQLException {
	
		if (result.hasErrors()) {
			map.addAttribute("userNamesList", transferFundsManager.listAllUserNames(principal.getName()));
			transferfundsForm.setFromAccountId(principal.getName());
			return "auth/trans/tranfunds";
		}
		
		if(!transferFundsManager.isValid(principal.getName(), transferfundsForm.getAmount() )){
			map.addAttribute("userNamesList", transferFundsManager.listAllUserNames(principal.getName()));
			transferfundsForm.setFromAccountId(principal.getName());
			map.addAttribute("AmountError","You dont have sufficient amount");
			return "auth/trans/tranfunds";
		}
		
		String response = req.getParameter("recaptcha_response_field");
		String challenge = req.getParameter("recaptcha_challenge_field");
		
		if(response == null || response.isEmpty()){
			map.addAttribute("captchaError","Captcha empty");
			map.addAttribute("userNamesList", transferFundsManager.listAllUserNames(principal.getName()));
			transferfundsForm.setFromAccountId(principal.getName());
			return "auth/trans/tranfunds";
		}
		if(challenge == null || challenge.isEmpty()){
			map.addAttribute("captchaError","Some one removed this challenge field");
			map.addAttribute("userNamesList", transferFundsManager.listAllUserNames(principal.getName()));
			transferfundsForm.setFromAccountId(principal.getName());
			return "auth/trans/tranfunds";
		}
		
		String remoteIpAddr = req.getHeader("X-FORWARDED-FOR");  
		if (remoteIpAddr == null) {  
			remoteIpAddr = req.getRemoteAddr();  
		}
		
		if(!reCaptchaManager.isValid(remoteIpAddr, challenge, response)){
			map.addAttribute("captchaError","Wrong captcha input");
			map.addAttribute("userNamesList", transferFundsManager.listAllUserNames(principal.getName()));
			transferfundsForm.setFromAccountId(principal.getName());
			return "auth/trans/tranfunds";
		}
		
		
		// Create the user object with the form data
		if(transferfundsForm.isValid()){
			TransferFunds transferFunds = transferFundsFactory.createTransferFundsInstance(transferfundsForm);
			transferFunds.setFromAccountId(principal.getName());
			logger.info(transferFunds.getFromAccountId());
			logger.info(transferFunds.getToAccountId());
			logger.info(""+transferFunds.getAmount());
			try {
				String transactionId = transferFundsManager.addTransferFunds(transferFunds, principal.getName());

				return "redirect:/auth/trans/otp/"+ transactionId;
			} catch (SQLException e) {
				logger.error("Error while adding Transaction",e);
			}
		}
		return "redirect:/auth/welcome";
	}

	
	@RequestMapping(value = "auth/trans/otp/{transactionId}", method = RequestMethod.GET)
	public String getToTransferFundOtpPage(  @PathVariable("transactionId") String transactionId,ModelMap model, Principal principal) {
		// If user is authorized
		model.addAttribute("oTPBackingBean", new OTPBackingBean());
		model.addAttribute("transactionId", transactionId);
		return "auth/trans/otp";
	}
	
	
	
	@RequestMapping(value = "auth/trans/otp/{transactionId}", method = RequestMethod.POST)
	public String postToTransferFundOtpPage(@PathVariable("transactionId") String transactionId, @Valid @ModelAttribute OTPBackingBean otpForm, BindingResult result, ModelMap map, Principal principal) {

		if(result.hasErrors()){
		
			ObjectError oe= result.getAllErrors().get(0);
	
			
			map.addAttribute("otperror", oe.getDefaultMessage());
			OTPBackingBean otpBackingBean = new OTPBackingBean();
			otpBackingBean.setOtp(otpForm.getOtp());
			map.addAttribute("oTPBackingBean",otpBackingBean );
			
			return "auth/trans/otp";
		}
		
		try {
			if(!otpManager.isTransactionOtpCorrect(otpForm, transactionId)){
				map.addAttribute("otperror", "OTP and transaction doesn't match");
				OTPBackingBean otpBackingBean = new OTPBackingBean();
				otpBackingBean.setOtp(otpForm.getOtp());
				map.addAttribute("oTPBackingBean",otpBackingBean );
				
				return "auth/trans/otp";
			}
		}catch (SQLException e) {
			logger.info(e.getMessage());
			return "auth/trans/fail";	
		} 
		
		if(otpForm.isValid()){
			try {
				TransferFunds transferFunds = otpManager.getTransferFunds(transactionId, principal.getName());				
				otpManager.updateTransaction(transactionId,transferFunds, principal.getName(), otpForm.getOtp());
			} catch (SQLException e) {
				logger.info(e.getMessage());
				return "auth/trans/fail";
			}
			return "auth/trans/success";
		}
		
		
		
		
		return "auth/trans/success";	
		
		
	}
}

