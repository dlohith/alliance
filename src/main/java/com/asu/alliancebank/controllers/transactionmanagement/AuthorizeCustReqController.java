/**
 * 
 */
package com.asu.alliancebank.controllers.transactionmanagement;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asu.alliancebank.controllers.transactionmanagement.backingbean.AuthorizeCustRequestBackingBean;
import com.asu.alliancebank.service.transaction.IAuthorizePaymentsManager;

/**
 * @author Kedar
 *
 */
@Controller
public class AuthorizeCustReqController {
	
	@Autowired
	private IAuthorizePaymentsManager authorizePaymentsManager;
	
	/**
	 * Populate the lists required for displaying
	 * @param model ModelMap object for this request
	 * @param principal Account related details are access using this
	 * @return go to correct page after this
	 * @throws SQLException 
	 */
	@RequestMapping(value = "auth/trans/authorizecustreq", method = RequestMethod.GET)
	public String getToAuthCustRequestPage( ModelMap model, Principal principal) throws SQLException {
		List<String> userList = authorizePaymentsManager.getUsersForCustAuth(principal.getName()) ;
		List<String> employeeList = authorizePaymentsManager.getBankEmployeeList(principal.getName());
		model.addAttribute("userList", userList);
		model.addAttribute("employeeList", employeeList);
		model.addAttribute("authorizeCustRequestBackingBean", new AuthorizeCustRequestBackingBean());
		return "auth/trans/authorizecustreq";
	}
	
	/**
	 * Call corresponding database functions
	 * @param model ModelMap object for this request
	 * @param principal Account related details are access using this
	 * @return go to correct page after this
	 * @throws SQLException 
	 */
	@RequestMapping(value = "auth/trans/authorizecustreq", method = RequestMethod.POST)
	public String getToAuthCustRequestPage(@Valid @ModelAttribute AuthorizeCustRequestBackingBean authReqBackingBean,
			BindingResult result, ModelMap model, Principal principal) throws SQLException {
		if (result.hasErrors()) {
			return "auth/trans/authorizecustreq";
		}	
		authorizePaymentsManager.authorizeCustAuthRequest(authReqBackingBean.getUserLoginID(), authReqBackingBean.getEmployeeLoginID(), principal.getName());
		return "redirect:/auth/trans";
	}
}
