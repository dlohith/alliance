/**
 * 
 */
package com.asu.alliancebank.controllers.accountmanagement;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asu.alliancebank.controllers.usermanagement.ListUserController;
import com.asu.alliancebank.service.account.IAccountManager;

/**
 * @author Kedar
 *
 */
@Controller
public class ListAccountController {

	@Autowired
	private IAccountManager accountManager;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ListUserController.class);
	
	@RequestMapping(value = "auth/acc/listaccount", method = RequestMethod.GET)
	public String listAccountPage( ModelMap model, Principal principal) {
		if(principal != null){
			try {
				List<String> loginIDs = accountManager.listAllUserLoginIdsFromAccounts(principal.getName());
				model.addAttribute("loginIDList", loginIDs);
				return "auth/acc/listaccount";
			} catch (SQLException e) {
				logger.error("Issue fetching account list from DB",e);
				
			}
		}
		
		return "auth/welcome";	
		
	}
}
