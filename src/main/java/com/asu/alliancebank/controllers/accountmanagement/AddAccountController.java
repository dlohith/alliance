/**
 * 
 */
package com.asu.alliancebank.controllers.accountmanagement;

import java.security.Principal;
import java.sql.SQLException;
import java.util.Map;

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
import com.asu.alliancebank.controllers.usermanagement.AddUserController;
import com.asu.alliancebank.domain.impl.Account;
import com.asu.alliancebank.domain.impl.User;
import com.asu.alliancebank.factory.IAccountFactory;
import com.asu.alliancebank.service.account.IAccountManager;

/**
 * @author Kedar
 *
 */
@Controller
public class AddAccountController {

	@Autowired
	private IAccountFactory accountFactory;
	
	@Autowired
	private IAccountManager accountManager;
	
	private static final Logger logger = LoggerFactory
			.getLogger(AddUserController.class);
	

	/**
	 * Helps create add {@link Account} form by getting the object ready for form jsp tags
	 * @param model ModelMap object for this request
	 * @param principal Account related details are access using this
	 * @return Prepares the Page for AddAccount form
	 */
	@RequestMapping(value = "auth/acc/addaccount", method = RequestMethod.GET)
	public String getToAddAccountPage( ModelMap model, Principal principal) {
		// If user is authorized
		model.addAttribute("accountBackingBean", new AccountBackingBean());
		Map<String, String> userList = accountManager.getUserListForAddAccount(principal.getName());
		model.addAttribute("userList", userList);
		return "auth/acc/addaccount";
	}
	
	/**
	 * Adds users by receiving the {@link User} details as object form
	 * @param accountForm This is a {@link AccountBackingBean} used to get data from front end form
	 * @param result {@link BindingResult} to check binding error
	 * @param map {@link ModelMap} for this request
	 * @return send the String for apache tiles to decide on the view 
	 */
	@RequestMapping(value = "auth/acc/addaccount", method = RequestMethod.POST)
	public String addNewAccount(@Valid @ModelAttribute AccountBackingBean accountForm, BindingResult result, ModelMap map, Principal principal) {
	
		if (result.hasErrors()) {
			return "auth/acc/addaccount";
		}	
		// Create the user object with the form data
		if(accountForm.isValid()){
			Account account = accountFactory.createAccountInstance(accountForm);
			try {
				accountManager.addAccount(account, principal.getName());
			} catch (SQLException e) {
				logger.error("Error while adding account",e);
			}
		}
		return "redirect:/auth/acc/accpanel";
	}

}
