package com.asu.alliancebank.controllers.transactionmanagement;

import java.security.Principal;
import java.sql.SQLException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asu.alliancebank.controllers.transactionmanagement.backingbean.TransferFundsBackingBean;
import com.asu.alliancebank.domain.impl.TransferFunds;
import com.asu.alliancebank.factory.ITransferFundsFactory;
import com.asu.alliancebank.service.transaction.ITransferFundsManager;

@Controller
public class TransferFundsController {
	
	@Autowired
	private ITransferFundsFactory transferFundsFactory;
	
	@Autowired
	private ITransferFundsManager transferFundsManager;
	
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
		model.addAttribute("transferFundsBackingBean", new TransferFundsBackingBean());
		return "auth/trans/tranfunds";
	}
	
	@RequestMapping(value = "auth/trans/tranfunds", method = RequestMethod.POST)
	public String transferfunds(@Valid @ModelAttribute TransferFundsBackingBean transferfundsForm, BindingResult result, ModelMap map, Principal principal) {
	
		if (result.hasErrors()) {
			return "auth/trans/tranfunds";
		}	
		// Create the user object with the form data
		if(transferfundsForm.isValid()){
			TransferFunds transferFunds = transferFundsFactory.createTransferFundsInstance(transferfundsForm);
			try {
				transferFundsManager.addTransferFunds(transferFunds, principal.getName());
			} catch (SQLException e) {
				logger.error("Error while adding Transaction",e);
			}
		}
		return "redirect:/auth/welcome";
		//return "/auth/trans/otp";
	}
	
}
