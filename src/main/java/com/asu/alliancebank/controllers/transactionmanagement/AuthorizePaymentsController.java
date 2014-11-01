/**
 * 
 */
package com.asu.alliancebank.controllers.transactionmanagement;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asu.alliancebank.domain.impl.MerchantRequest;
import com.asu.alliancebank.domain.impl.Transaction;
import com.asu.alliancebank.service.transaction.IAuthorizePaymentsManager;

/**
 * @author Kedar
 *
 */
@Controller
public class AuthorizePaymentsController {
	
	@Autowired
	private IAuthorizePaymentsManager authorizePaymentsManager;
	
	/**
	 * Helps create add {@link Transaction} form by getting the object ready for form jsp tags
	 * @param model ModelMap object for this request
	 * @param principal Transaction related details are access using this
	 * @return Prepares the Page for AddTransaction form
	 * @throws SQLException 
	 */
	@RequestMapping(value = "auth/trans/authorizepayments", method = RequestMethod.GET)
	public String getToAuthorizePaymentPage( ModelMap model, Principal principal) throws SQLException {
		List<MerchantRequest> authorizeList = authorizePaymentsManager.getPendingRequests(principal.getName());
		model.addAttribute("authorizeList", authorizeList);
		return "auth/trans/authorizepayments";
	}
}
