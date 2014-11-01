/**
 * 
 */
package com.asu.alliancebank.controllers.transactionmanagement;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

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

import com.asu.alliancebank.controllers.accountmanagement.backingbean.AccountBackingBean;
import com.asu.alliancebank.controllers.transactionmanagement.backingbean.MerchantDebitFundsBackingBean;
import com.asu.alliancebank.controllers.usermanagement.AddUserController;
import com.asu.alliancebank.service.account.IAccountManager;
import com.asu.alliancebank.service.transaction.IMerchantFundsManager;
import com.asu.alliancebank.service.transaction.ITransferFundsManager;

/**
 * @author Kedar
 *
 */
@Controller
public class MerchantDebitFundsController {
	
	@Autowired
	private IAccountManager accountManager;
	
	@Autowired
	private IMerchantFundsManager merchantFundsManager;
	
	@Autowired
	private ITransferFundsManager transferFundsManager;
	
	private static final Logger logger = LoggerFactory
			.getLogger(AddUserController.class);
	
	/**
	 * Helps by getting the object ready for form jsp tags
	 * @param model ModelMap object for this request
	 * @param principal related details are access using this
	 * @return Prepares the Page for merchant funds form
	 */
	@RequestMapping(value = "auth/trans/merchantpayments", method = RequestMethod.GET)
	public String getToMerchantFundsPage( ModelMap model, Principal principal) {
		// If user is authorized
		model.addAttribute("merchantDebitFundsBackingBean", new MerchantDebitFundsBackingBean());
		List<String> userList;
		try {
			userList = transferFundsManager.listAllUserNames(principal.getName());
		} catch (SQLException e) {
			logger.error("Error while getting user list - ");
			return "auth/trans/merchantpayments";
		}
		model.addAttribute("userList", userList);
		return "auth/trans/merchantpayments";
	}
	
	/**
	 * @param  This is a {@link AccountBackingBean} used to get data from front end form
	 * @param result {@link BindingResult} to check binding error
	 * @param map {@link ModelMap} for this request
	 * @return send the String for apache tiles to decide on the view 
	 */
	@RequestMapping(value = "auth/trans/merchantpayments", method = RequestMethod.POST)
	public String addNewMerchantRequest(@Valid @ModelAttribute MerchantDebitFundsBackingBean merchantBackingBean, BindingResult result, ModelMap map, Principal principal) {
	
		if (result.hasErrors()) {
			return "auth/trans/merchantpayments";
		}	
		// Create the user object with the form data
		try {
				merchantFundsManager.addMerchantRequest(merchantBackingBean, principal.getName());
			} catch (SQLException e) {
				logger.error("Error while adding merchant request",e);
			}
		return "redirect:/auth/trans/merchantpayments";
	}
	
	
	
}
