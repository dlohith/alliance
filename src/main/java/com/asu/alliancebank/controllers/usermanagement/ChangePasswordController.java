package com.asu.alliancebank.controllers.usermanagement;

import java.security.Principal;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asu.alliancebank.controllers.usermanagement.backingbean.ChangePasswordBackingBean;
import com.asu.alliancebank.domain.impl.User;
import com.asu.alliancebank.service.user.IUserManager;

@Controller
public class ChangePasswordController {
	
	@Autowired
	private IUserManager userManager;
	

	@RequestMapping(value = "changepassword", method = RequestMethod.GET)
	public String getToAddUserPage( ModelMap model, Principal principal) {
		
		// If user is authorized
		model.addAttribute("changePasswordBackingBean", new ChangePasswordBackingBean());
		return "changepassword";
	}
	
	
	@RequestMapping(value = "changepassword", method = RequestMethod.POST)
	public String addNewUser(HttpServletRequest req, @Valid @ModelAttribute ChangePasswordBackingBean changePasswordform, BindingResult result, ModelMap map, Principal principal) {
		if (result.hasErrors()) {
			return "changepassword";
		}	
		
		try {
			User user =  userManager.getUserDetails(changePasswordform.getLoginId());
			if(user == null|| !BCrypt.checkpw(changePasswordform.getOldPassword(), user.getPassword())){
				map.addAttribute("loginerror","either login id is wrong");
				map.addAttribute("oldpasserror","either old password is wrong");
				return "changepassword";
			}
			
			
		} catch (SQLException e) {

		}
			
			
		try {
			if(!userManager.isFirstTimeLogin(changePasswordform.getLoginId())){
				map.addAttribute("loginerror","This is not first time login, we won't allow you to change password :D :P");
				return "changepassword";
			}
		} catch (SQLException e) {

		}
			
			
		
		if(!changePasswordform.isValid()){
			map.addAttribute("newpasserror","New Password and repeat password doesn't match");
			map.addAttribute("repeatnewpasserror","New Password and repeat password doesn't match");
			return "changepassword";
		}
		
		try {
			userManager.changePass(changePasswordform.getLoginId(), changePasswordform.getNewPassword());
		} catch (SQLException e) {
		}
		
		return "redirect:/login";
	}
	

}
