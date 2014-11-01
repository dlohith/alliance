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

import com.asu.alliancebank.controllers.transactionmanagement.backingbean.DebitFundsBackingBean;
import com.asu.alliancebank.domain.impl.DebitFunds;
import com.asu.alliancebank.domain.impl.Transaction;
import com.asu.alliancebank.factory.IDebitFundsFactory;
import com.asu.alliancebank.service.transaction.IDebitFundsManager;
import com.asu.alliancebank.service.transaction.ITransferFundsManager;

@Controller
public class DebitFundsController {
	@Autowired
	private IDebitFundsFactory debitFundsFactory;
	
	@Autowired
	private IDebitFundsManager debitFundsManager;
	
	@Autowired
	private ITransferFundsManager transferFundsManager;
	
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
		// If user is authorized
		model.addAttribute("debitFundsBackingBean", new DebitFundsBackingBean());
		return "auth/trans/debitfunds";
	}
	
	@RequestMapping(value = "auth/trans/debitfunds", method = RequestMethod.POST)
	public String creditfunds(ModelMap model, @Valid @ModelAttribute DebitFundsBackingBean debitfundsForm, BindingResult result, ModelMap map, Principal principal) throws SQLException {
	
		if (result.hasErrors()) {							
			return "auth/trans/debitfunds";
		}
		
		if(!transferFundsManager.isValid(principal.getName(), debitfundsForm.getAmount())){						
			model.addAttribute("AmountError","You dont have sufficient amount");
			return "auth/trans/debitfunds";
		}
		
		// Create the user object with the form data
		if(debitfundsForm.isValid()){
			DebitFunds debitFunds = debitFundsFactory.createDebitFundsInstance(debitfundsForm);
			try {
				debitFundsManager.addDebitFunds(debitFunds, principal.getName());
			} catch (SQLException e) {
				logger.error("Error while adding Debit Transaction",e);
			}
		}
		return "redirect:/auth/trans";
	}
	

}
