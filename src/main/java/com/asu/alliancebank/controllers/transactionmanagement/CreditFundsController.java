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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asu.alliancebank.controllers.transactionmanagement.backingbean.CreditFundsBackingBean;
import com.asu.alliancebank.controllers.transactionmanagement.backingbean.FinalizeCreditFundsBackingBean;
import com.asu.alliancebank.controllers.usermanagement.AddUserController;
import com.asu.alliancebank.domain.ICreditFunds;
import com.asu.alliancebank.domain.impl.CreditFunds;
import com.asu.alliancebank.domain.impl.Transaction;
import com.asu.alliancebank.factory.ICreditFundsFactory;
import com.asu.alliancebank.recaptcha.IReCaptchaManager;
import com.asu.alliancebank.security.pki.impl.PKIManager;
import com.asu.alliancebank.service.account.IAccountManager;
import com.asu.alliancebank.service.transaction.ICreditFundsManager;
import com.asu.alliancebank.service.transaction.ITransactionManager;

@Controller
public class CreditFundsController {
	@Autowired
	private ICreditFundsFactory creditFundsFactory;
	
	@Autowired
	private ICreditFundsManager creditFundsManager;
	
	@Autowired
	private ITransactionManager transactionManager;
	
	@Autowired
	private IAccountManager accountManager;
	
	@Autowired
	private IReCaptchaManager reCaptchaManager;
	
	@Autowired
	private PKIManager pkiManager;
	
	private static final Logger logger = LoggerFactory
			.getLogger(AddUserController.class);
	
	/**
	 * Helps create add {@link Transaction} form by getting the object ready for form jsp tags
	 * @param model ModelMap object for this request
	 * @param principal Transaction related details are access using this
	 * @return Prepares the Page for AddTransaction form
	 * @throws SQLException 
	 */
	@RequestMapping(value = "auth/trans/creditfunds", method = RequestMethod.GET)
	public String getToAddTransactionPage( ModelMap model, Principal principal) {
		
		try {
			if(!accountManager.hasAccount(principal.getName())){
				model.addAttribute("error", "Account not created, ask your admin to create account for you");
				return "redirect:/auth/trans";
			}
		} catch (SQLException e) {
			model.addAttribute("error", "Some error in page");
			return "redirect:/auth/trans";
		}
		
		// If user is authorized
		model.addAttribute("creditFundsBackingBean", new CreditFundsBackingBean());
		return "auth/trans/creditfunds";
	}
	
	@RequestMapping(value = "auth/trans/creditfunds", method = RequestMethod.POST)
	public String creditfunds(HttpServletRequest req, ModelMap model, @Valid @ModelAttribute CreditFundsBackingBean creditfundsForm, BindingResult result, ModelMap map, Principal principal) throws SQLException {
	
		if (result.hasErrors()) {
			model.addAttribute("creditFundsBackingBean", new CreditFundsBackingBean());
			return "auth/trans/creditfunds";
		}	
		
		if(!creditFundsManager.isValid(principal.getName(), creditfundsForm.getAmount())){
			model.addAttribute("AmountError","Total balance exceeding max limit of 100000");
			return "auth/trans/creditfunds";
		}

		
		String response = req.getParameter("recaptcha_response_field");
		String challenge = req.getParameter("recaptcha_challenge_field");
		
		if(response == null || response.isEmpty()){
			map.addAttribute("captchaError","Captcha empty");
			return "auth/trans/creditfunds";
		}
		if(challenge == null || challenge.isEmpty()){
			map.addAttribute("captchaError","Some one removed this challenge field");
			return "auth/trans/creditfunds";
		}
		
		String remoteIpAddr = req.getHeader("X-FORWARDED-FOR");  
		if (remoteIpAddr == null) {  
			remoteIpAddr = req.getRemoteAddr();  
		}
		
		if(!reCaptchaManager.isValid(remoteIpAddr, challenge, response)){
			map.addAttribute("captchaError","Wrong captcha input");
			return "auth/trans/creditfunds";
		}
		
		
		// Create the user object with the form data
		if(creditfundsForm.isValid()){
			CreditFunds creditFunds = creditFundsFactory.createCreditFundsInstance(creditfundsForm);
			try {
				String transactionId = creditFundsManager.addCreditFunds(creditFunds, principal.getName());
				return "redirect:/auth/trans/hash/"+transactionId;
			} catch (SQLException e) {
				logger.error("Error while adding Credit Transaction",e);
				return "auth/trans/fail";
			}
		}
		return "auth/trans/hash";
	}
	
	
	@RequestMapping(value = "auth/trans/hash/{transactionId}", method = RequestMethod.GET)
	public String getToCreditHashPage(  @PathVariable("transactionId") String transactionId,ModelMap model, Principal principal) {
		// If user is authorized
		model.addAttribute("finalizeCreditFundsBackingBean", new FinalizeCreditFundsBackingBean());
		model.addAttribute("transactionId", transactionId);
		return "auth/trans/hash";

	}
	
	
	@RequestMapping(value = "auth/trans/hash/{transactionId}", method = RequestMethod.POST)
	public String postToTransferFundOtpPage(@PathVariable("transactionId") String transactionId, @Valid @ModelAttribute FinalizeCreditFundsBackingBean hashForm, BindingResult result, ModelMap map, Principal principal) {
		System.out.println("POST hash : came her");
		if(result.hasErrors()){
			map.addAttribute("transactionId", transactionId);
			return "auth/trans/hash";
		}
		
		try {
			if(!pkiManager.isResponseValidWithHashedString(transactionId, hashForm.getEncrypt(), principal.getName(),ITransactionManager.CREDIT)){
				map.addAttribute("EncryptionError", "Encrypted string and transaction doesn't match");
				map.addAttribute("transactionId", transactionId);
				return "auth/trans/hash";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "auth/trans/fail";
		}
		

		
		if(hashForm.isValid()){
			
			try {
				ICreditFunds creditFunds =creditFundsManager.getCreditFunds(transactionId);
				creditFundsManager.finalizeTransactionCredit(transactionId, creditFunds, principal.getName());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			try {
//				TransferFunds transferFunds = otpManager.getTransferFunds(transactionId, principal.getName());				
//				otpManager.updateTransaction(transactionId,transferFunds, principal.getName(), otpForm.getOtp());
//			} catch (SQLException e) {
//				logger.info(e.getMessage());
//				return "auth/trans/fail";
//			}
			return "auth/trans/success";
		}
		
		
		
		
		return "auth/trans/success";	
		
		
	}

}
