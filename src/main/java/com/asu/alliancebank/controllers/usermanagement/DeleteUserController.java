package com.asu.alliancebank.controllers.usermanagement;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asu.alliancebank.service.user.IUserManager;

@Controller
public class DeleteUserController {

	@Autowired
	private IUserManager userManager;
	
	private static final Logger logger = LoggerFactory
			.getLogger(DeleteUserController.class);
	
	@RequestMapping(value = "auth/user/deleteuser", method = RequestMethod.POST)
	public String deleteUser( HttpServletRequest req, ModelMap model, Principal principal) {
		
		String[] values = req.getParameterValues("selected");
		
		
		if(values == null){
			logger.info("No user selected");
		}else{
			
			try {
				List<String> userIdMarkedForDeletion = userManager.checkDeleteUserIds(values, principal.getName());
				userManager.deleteUsers(userIdMarkedForDeletion);
			} catch (SQLException e) {
				logger.error("DB Issue while deleting user",e);
			}
		}

		
		return "redirect:/auth/user/listuser";
	}
		
}
