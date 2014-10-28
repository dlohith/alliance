package com.asu.alliancebank.controllers.usermanagement;

import java.security.Principal;
import java.sql.SQLException;

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
public class UserProfileController {

	@Autowired
	private IUserManager userManager;
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserProfileController.class);
	
	
	@RequestMapping(value = "auth/myprofile", method = RequestMethod.GET)
	public String getToModifyUserPage(ModelMap model, Principal principal) {
		
		String loginId = principal.getName();
		User user = null;
		try {
			user = userManager.getUserDetails(loginId);
		} catch (SQLException e) {
			logger.error("Issue while getting user details",e);
		}
		
		if(user == null){
			return "redirect:/auth/welcome";
		}
		
		
		// If user is authorized
		model.addAttribute("username",loginId);
		model.addAttribute("user",user);
		return "auth/myprofile";
	}
}
