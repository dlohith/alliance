package com.asu.alliancebank.controllers.usermanagement;

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

import com.asu.alliancebank.domain.impl.User;
import com.asu.alliancebank.service.user.IUserManager;

@Controller
public class ListUserController {

	
	@Autowired
	private IUserManager userManager;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ListUserController.class);
	
	@RequestMapping(value = "auth/user/listuser", method = RequestMethod.GET)
	public String listUserPage( ModelMap model, Principal principal) {
		if(principal != null){
			try {
				List<User> users = userManager.listAllUser(principal.getName());
				model.addAttribute("userList", users);
				return "auth/user/listuser";
			} catch (SQLException e) {
				logger.error("Issue fetching user list from DB",e);
				
			}
		}
		
		return "auth/welcome";	
		
	}
	
}
