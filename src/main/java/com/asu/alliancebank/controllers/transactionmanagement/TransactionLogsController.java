/**
 * 
 */
package com.asu.alliancebank.controllers.transactionmanagement;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asu.alliancebank.controllers.usermanagement.AddUserController;
import com.asu.alliancebank.domain.ITransactionCredit;
import com.asu.alliancebank.domain.ITransactionDebit;
import com.asu.alliancebank.security.checkcapability.ICheckUserRoleCapability;
import com.asu.alliancebank.service.transaction.ICreditFundsManager;
import com.asu.alliancebank.service.transaction.IDebitFundsManager;

/**
 * @author Kedar
 *
 */
@Controller
public class TransactionLogsController {
	
	@Autowired
	private ICreditFundsManager creditFundsManager;
	
	@Autowired
	private IDebitFundsManager debitFundsManager;
	
	@Autowired
	private ICheckUserRoleCapability checkCompatibility;
	
	private static final Logger logger = LoggerFactory
			.getLogger(AddUserController.class);
	
	/**
	 * Helps create add list of transactions by getting the object ready for form jsp tags
	 * @param model ModelMap object for this request
	 * @param principal Transaction related details are access using this
	 * @return Prepares the Page for display
	 * @throws SQLException 
	 */
	@RequestMapping(value = "auth/trans/transactionlogs", method = RequestMethod.GET)
	public String populateListsForJSP( ModelMap model, Principal principal) throws SQLException {
		List<ITransactionCredit> creditDetails = null;
		List<ITransactionDebit> debitDetails = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		// populate the lists as per role of logged in user. Customers should see only their logs,
		// admins can see everything
		if(checkCompatibility.isIndividualRole(auth) || checkCompatibility.isMerchantRole(auth)) {
			creditDetails = creditFundsManager.getCreditDetailsCust(principal.getName());
			debitDetails = debitFundsManager.getDebitDetailsCust(principal.getName());
		}
		if(checkCompatibility.isAdminRole(auth) || checkCompatibility.isBankEmployeeRole(auth) ) {
			creditDetails = creditFundsManager.getCreditDetails(principal.getName());
			debitDetails = debitFundsManager.getDebitDetails(principal.getName());
		}
		model.addAttribute("transactionCreditList", creditDetails);
		model.addAttribute("transactionDebitList", debitDetails);
		return "auth/trans/transactionlogs";
	}
}
