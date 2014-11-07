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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asu.alliancebank.domain.impl.MerchantRequest;
import com.asu.alliancebank.security.checkcapability.ICheckUserRoleCapability;
import com.asu.alliancebank.service.account.IAccountManager;
import com.asu.alliancebank.service.transaction.IAuthorizePaymentsManager;
import com.asu.alliancebank.service.transaction.IMerchantFundsManager;

/**
 * @author Kedar
 *
 */
@Controller
public class ApproveMerchantFundsController {
	
	@Autowired
	private IAccountManager accountManager;
	
	@Autowired
	private IMerchantFundsManager merchantFundsManager;
	
	@Autowired
	private IAuthorizePaymentsManager authorizePaymentsManager;
	
	@Autowired
	private ICheckUserRoleCapability checkCompatibility;
	

	/**
	 * Come here when user clicks on Approve button
	 * @param model ModelMap object for this request
	 * @param principal Transaction related details are access using this
	 * @throws SQLException 
	 */
	@RequestMapping(value = "auth/trans/approve/{requestID}", method = RequestMethod.GET)
	public String callApprovePayment(@PathVariable("requestID") String requestID, ModelMap model, Principal principal) throws SQLException {
		MerchantRequest merchantRequest = authorizePaymentsManager.getMerchantRequest(requestID);
		if(!authorizePaymentsManager.areContentsValid(merchantRequest.getMerchantID(), merchantRequest.getUserLoginID(), principal.getName()))
				return "auth/trans/authorizepayments";
		
		String userId = merchantFundsManager.getUserIdForMerchantRequests(requestID);
		
		if(userId == null || userId.isEmpty()){
			return "auth/trans/authorizepayments";
		}
		
		Double amount = merchantFundsManager.getAmountForMerchantRequests(requestID);
		
		if(amount == null ){
			return "auth/trans/authorizepayments";
		}
		
		double balance = accountManager.getAccountBalance(userId);
		
		if(balance < amount){
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
			model.addAttribute("error","Not enough balance in user account");
			return "auth/trans/authorizepayments";
		}
		
		
		
		authorizePaymentsManager.approvePayment(merchantRequest, principal.getName());
		return "redirect:/auth/trans";
	}
}
