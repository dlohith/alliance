/**
 * 
 */
package com.asu.alliancebank.controllers.transactionmanagement;

import java.security.Principal;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asu.alliancebank.service.transaction.IAuthorizePaymentsManager;

/**
 * @author Kedar
 *
 */
@Controller
public class AddCustAuthRequestController {
	
	@Autowired
	private IAuthorizePaymentsManager authorizePaymentsManager;
	
	/**
	 * Add customer request into database
	 * @param model ModelMap object for this request
	 * @param principal Transaction related details are access using this
	 * @return Prepares the Page for AddTransaction form
	 * @throws SQLException 
	 */
	@RequestMapping(value = "auth/trans/addauthrequest", method = RequestMethod.GET)
	public String getToAddTransactionPage( ModelMap model, Principal principal) throws SQLException {
		authorizePaymentsManager.addCustAuthRequest(principal.getName());
		return "auth/trans/authreqsuccess";
	}
}
