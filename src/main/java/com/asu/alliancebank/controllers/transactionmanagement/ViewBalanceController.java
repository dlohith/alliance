package com.asu.alliancebank.controllers.transactionmanagement;

import java.security.Principal;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asu.alliancebank.service.account.IAccountManager;

@Controller
public class ViewBalanceController {
	@Autowired
	private IAccountManager accountManager;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ViewBalanceController.class);
	
	
	@RequestMapping(value = "auth/trans/viewbalance", method = RequestMethod.GET)
	public String getToViewBalancePage(ModelMap model, Principal principal) {
		
		String loginId = principal.getName();
		Double balance = null;
		try {			
				if(!accountManager.hasAccount(principal.getName())){
					model.addAttribute("error", "Account not created, ask your admin to create account for you");
					return "redirect:/auth/trans";
				}			
			balance = accountManager.getAccountBalance(loginId);
		} catch (SQLException e) {
			logger.error("Issue while getting balance details",e);
		}
		
		if(balance == null){
			return "redirect:/auth/welcome";
		}
		
		
		// If user is authorized
		model.addAttribute("username",loginId);
		model.addAttribute("balance",balance);
		return "auth/trans/viewbalance";
	}
}
