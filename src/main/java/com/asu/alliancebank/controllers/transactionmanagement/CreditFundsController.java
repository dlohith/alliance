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


import com.asu.alliancebank.controllers.transactionmanagement.backingbean.CreditFundsBackingBean;
import com.asu.alliancebank.controllers.usermanagement.AddUserController;
import com.asu.alliancebank.domain.impl.Transaction;
import com.asu.alliancebank.domain.impl.CreditFunds;
import com.asu.alliancebank.factory.ICreditFundsFactory;
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
	
	private static final Logger logger = LoggerFactory
			.getLogger(AddUserController.class);
	
	/**
	 * Helps create add {@link Transaction} form by getting the object ready for form jsp tags
	 * @param model ModelMap object for this request
	 * @param principal Transaction related details are access using this
	 * @return Prepares the Page for AddTransaction form
	 */
	@RequestMapping(value = "auth/trans/creditfunds", method = RequestMethod.GET)
	public String getToAddTransactionPage( ModelMap model, Principal principal) {
		// If user is authorized
		model.addAttribute("creditFundsBackingBean", new CreditFundsBackingBean());
		return "auth/trans/creditfunds";
	}
	
	@RequestMapping(value = "auth/trans/creditfunds", method = RequestMethod.POST)
	public String creditfunds(ModelMap model, @Valid @ModelAttribute CreditFundsBackingBean creditfundsForm, BindingResult result, ModelMap map, Principal principal) throws SQLException {
	
		if (result.hasErrors()) {
			model.addAttribute("creditFundsBackingBean", new CreditFundsBackingBean());
			return "auth/trans/creditfunds";
		}	
		
		if(!creditFundsManager.isValid(principal.getName(), creditfundsForm.getAmount())){
			model.addAttribute("AmountError","Total balance exceeding max limit of 100000");
			return "auth/trans/creditfunds";
		}
		
		if(!transactionManager.isValidEncryptedString(creditfundsForm.getEncrypt(), principal.getName())){
			model.addAttribute("EncryptionError","Invalid encrypt string");
			return "auth/trans/creditfunds";
		}
		
		
		// Create the user object with the form data
		if(creditfundsForm.isValid()){
			CreditFunds creditFunds = creditFundsFactory.createCreditFundsInstance(creditfundsForm);
			try {
				creditFundsManager.addCreditFunds(creditFunds, principal.getName());
			} catch (SQLException e) {
				logger.error("Error while adding Credit Transaction",e);
				return "auth/trans/fail";
			}
		}
		return "auth/trans/success";
	}
	

}
