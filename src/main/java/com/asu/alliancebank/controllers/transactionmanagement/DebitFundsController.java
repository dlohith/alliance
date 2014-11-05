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

import com.asu.alliancebank.controllers.transactionmanagement.backingbean.DebitFundsBackingBean;
import com.asu.alliancebank.controllers.transactionmanagement.backingbean.FinalizeCreditFundsBackingBean;
import com.asu.alliancebank.domain.IDebitFunds;
import com.asu.alliancebank.domain.impl.DebitFunds;
import com.asu.alliancebank.domain.impl.Transaction;
import com.asu.alliancebank.factory.IDebitFundsFactory;
import com.asu.alliancebank.recaptcha.IReCaptchaManager;
import com.asu.alliancebank.security.pki.impl.PKIManager;
import com.asu.alliancebank.service.account.IAccountManager;
import com.asu.alliancebank.service.transaction.IDebitFundsManager;
import com.asu.alliancebank.service.transaction.ITransactionManager;
import com.asu.alliancebank.service.transaction.ITransferFundsManager;

@Controller
public class DebitFundsController {
	@Autowired
	private IDebitFundsFactory debitFundsFactory;
	
	@Autowired
	private IDebitFundsManager debitFundsManager;
	
	@Autowired
	private ITransferFundsManager transferFundsManager;
	
	@Autowired
	private ITransactionManager transactionManager;
	
	@Autowired
	private PKIManager pkiManager;
	
	@Autowired
	private IReCaptchaManager reCaptchaManager;
	
	@Autowired
	private IAccountManager accountManager;
	
	
	private static final Logger logger = LoggerFactory
			.getLogger(DebitFundsController.class);
	
	/**
	 * Helps create add {@link Transaction} form by getting the object ready for form jsp tags
	 * @param model ModelMap object for this request
	 * @param principal Transaction related details are access using this
	 * @return Prepares the Page for AddTransaction form
	 */
	@RequestMapping(value = "auth/trans/debitfunds", method = RequestMethod.GET)
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
		model.addAttribute("debitFundsBackingBean", new DebitFundsBackingBean());
		return "auth/trans/debitfunds";
	}
	
	@RequestMapping(value = "auth/trans/debitfunds", method = RequestMethod.POST)
	public String creditfunds(HttpServletRequest req, ModelMap model, @Valid @ModelAttribute DebitFundsBackingBean debitfundsForm, BindingResult result, ModelMap map, Principal principal) throws SQLException {
	
		if (result.hasErrors()) {							
			return "auth/trans/debitfunds";
		}
		
		if(!transferFundsManager.isValid(principal.getName(), debitfundsForm.getAmount())){						
			model.addAttribute("AmountError","You dont have sufficient amount");
			return "auth/trans/debitfunds";
		}
		
		String response = req.getParameter("recaptcha_response_field");
		String challenge = req.getParameter("recaptcha_challenge_field");
		
		if(response == null || response.isEmpty()){
			map.addAttribute("captchaError","Captcha empty");
			return "auth/trans/debitfunds";
		}
		if(challenge == null || challenge.isEmpty()){
			map.addAttribute("captchaError","Some one removed this challenge field");
			return "auth/trans/debitfunds";
		}
		
		String remoteIpAddr = req.getHeader("X-FORWARDED-FOR");  
		if (remoteIpAddr == null) {  
			remoteIpAddr = req.getRemoteAddr();  
		}
		
		if(!reCaptchaManager.isValid(remoteIpAddr, challenge, response)){
			map.addAttribute("captchaError","Wrong captcha input");
			return "auth/trans/debitfunds";
		}
		
		
		// Create the user object with the form data
		if(debitfundsForm.isValid()){
			DebitFunds debitFunds = debitFundsFactory.createDebitFundsInstance(debitfundsForm);
			try {
				String transactionId = debitFundsManager.addDebitFunds(debitFunds, principal.getName());
				return "redirect:/auth/trans/debithash/"+transactionId;
			} catch (SQLException e) {
				logger.error("Error while adding Debit Transaction",e);
				return "auth/trans/fail";
			}
		}
		return "redirect:/auth/trans/success";
	}
	
	
	
	@RequestMapping(value = "auth/trans/debithash/{transactionId}", method = RequestMethod.GET)
	public String getToCreditHashPage(  @PathVariable("transactionId") String transactionId,ModelMap model, Principal principal) {
		// If user is authorized
		System.out.println("GET : came here");
		model.addAttribute("finalizeCreditFundsBackingBean", new FinalizeCreditFundsBackingBean());
		model.addAttribute("transactionId", transactionId);
		return "auth/trans/debithash";

	}
	
	
	@RequestMapping(value = "auth/trans/debithash/{transactionId}", method = RequestMethod.POST)
	public String postToTransferFundOtpPage(@PathVariable("transactionId") String transactionId, @Valid @ModelAttribute FinalizeCreditFundsBackingBean hashForm, BindingResult result, ModelMap map, Principal principal) {
		System.out.println("POST : came here");
		if(result.hasErrors()){
			System.out.println("errors ");
			map.addAttribute("transactionId", transactionId);
			return "auth/trans/debithash";
		}
		
		try {
			if(!pkiManager.isResponseValidWithHashedString(transactionId, hashForm.getEncrypt(), principal.getName(),ITransactionManager.DEBIT)){
				map.addAttribute("EncryptionError", "Encrypted string and transaction doesn't match");
				map.addAttribute("transactionId", transactionId);
				return "auth/trans/debithash";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "auth/trans/fail";
		}
		

		
		if(hashForm.isValid()){
			
			try {
				IDebitFunds debitFunds =debitFundsManager.getDebitFunds(transactionId);
				debitFundsManager.finalizeTransactionDebit(transactionId, debitFunds, principal.getName());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return "auth/trans/success";
		}
		
		
		
		
		return "auth/trans/success";	
		
		
	}

}
