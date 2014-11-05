/**
 * 
 */
package com.asu.alliancebank.controllers.transactionmanagement;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asu.alliancebank.domain.impl.MerchantRequest;
import com.asu.alliancebank.domain.impl.Transaction;
import com.asu.alliancebank.security.checkcapability.ICheckUserRoleCapability;
import com.asu.alliancebank.service.transaction.IAuthorizePaymentsManager;

/**
 * @author Kedar
 *
 */
@Controller
public class AuthorizePaymentsController {
	
	@Autowired
	private IAuthorizePaymentsManager authorizePaymentsManager;
	
	@Autowired
	private ICheckUserRoleCapability checkCompatibility;
	
	/**
	 * Helps create add {@link Transaction} form by getting the object ready for form jsp tags
	 * @param model ModelMap object for this request
	 * @param principal Transaction related details are access using this
	 * @return Prepares the Page for AddTransaction form
	 * @throws SQLException 
	 */
	@RequestMapping(value = "auth/trans/authorizepayments", method = RequestMethod.GET)
	public String getToAuthorizePaymentPage( ModelMap model, Principal principal) throws SQLException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<MerchantRequest> employeeMerchantAuthList = null;
		if(checkCompatibility.isBankEmployeeRole(auth))  {
			employeeMerchantAuthList = authorizePaymentsManager.getPendingMerchantRequestsEmployee(principal.getName());
		}
		List<MerchantRequest> finalList = authorizePaymentsManager.getPendingRequests(principal.getName());
		if(employeeMerchantAuthList != null && employeeMerchantAuthList.size() > 0) {
			finalList.addAll(employeeMerchantAuthList);
		}
		model.addAttribute("authorizeList", finalList);
		return "auth/trans/authorizepayments";
	}
}
