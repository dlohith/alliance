/**
 * 
 */
package com.asu.alliancebank.controllers.transactionmanagement;

import java.security.Principal;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asu.alliancebank.domain.impl.MerchantRequest;
import com.asu.alliancebank.service.transaction.IAuthorizePaymentsManager;

/**
 * @author Kedar
 *
 */
@Controller
public class RejectMerchantFundsController {
	@Autowired
	private IAuthorizePaymentsManager authorizePaymentsManager;
	/**
	 * Come here when user clicks on Reject button
	 * @param model ModelMap object for this request
	 * @param principal Transaction related details are access using this
	 * @throws SQLException 
	 */
	@RequestMapping(value = "auth/trans/reject/{requestID}", method = RequestMethod.GET)
	public String callApprovePayment(@PathVariable("requestID") String requestID, ModelMap model, Principal principal) throws SQLException {
		MerchantRequest merchantRequest = authorizePaymentsManager.getMerchantRequest(requestID);
		if(!authorizePaymentsManager.areContentsValid(merchantRequest.getMerchantID(), merchantRequest.getUserLoginID(), principal.getName()))
			return "auth/trans/authorizepayments";
		authorizePaymentsManager.rejectPayment(merchantRequest, principal.getName());
		return "redirect:/auth/trans";
	}
}
