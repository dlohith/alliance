package com.asu.alliancebank.controllers.usermanagement;

import java.security.Principal;
import java.sql.SQLException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asu.alliancebank.controllers.usermanagement.backingbean.ModifyUserBackingBean;
import com.asu.alliancebank.controllers.usermanagement.backingbean.UserTranslator;
import com.asu.alliancebank.domain.impl.User;
import com.asu.alliancebank.factory.IUserFactory;
import com.asu.alliancebank.service.role.IRoleManager;
import com.asu.alliancebank.service.user.IUserManager;

@Controller
public class ModifyUserController {

	@Autowired
	private IUserManager userManager;
	
	@Autowired
	private UserTranslator userTranslator;
	
	@Autowired
	private IRoleManager roleManager;
	
	@Autowired
	private IUserFactory userFactory;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ModifyUserController.class);
	
	/**
	 * Helps modify {@link User} form by getting the object ready for form jsp tags
	 * @param model ModelMap object for this request
	 * @param principal User related details are access using this
	 * @return Prepares the Page for Modify User form
	 */
	@RequestMapping(value = "auth/user/modifyuser/{loginid}", method = RequestMethod.GET)
	public String getToModifyUserPage(@PathVariable("loginid") String loginId, ModelMap model, Principal principal) {
		
		if(loginId == null){
			return "redirect:/auth/user/listuser";
		}
		User user = null;
		try {
			user = userManager.getUserDetails(loginId);
		} catch (SQLException e) {
			logger.error("Issue while getting user details",e);
		}
		
		if(user == null){
			return "redirect:/auth/user/listuser";
		}
		
		
		
		// If user is authorized
		model.addAttribute("username",loginId);
		model.addAttribute("availableRoles",roleManager.getRolesList());
		model.addAttribute("modifyUserBackingBean", userTranslator.convertUserToModifyUserBackingBean(user));
		return "auth/user/modifyuser";
	}
	
	@RequestMapping(value = "auth/user/modifyuser/{loginid}", method = RequestMethod.POST)
	public String postFromModifyUserPage(@PathVariable("loginid") String loginId,@Valid @ModelAttribute ModifyUserBackingBean userForm, BindingResult result, ModelMap map, Principal principal) {

		if(loginId == null || loginId.isEmpty()){
			return "redirect:/auth/user/listuser";
		}
		
		// If user data has validation issues
		if (result.hasErrors()) {
			map.addAttribute("username",loginId);
			map.addAttribute("availableRoles", roleManager.getRoles());
			return "auth/user/modifyuser";
		}		
		
		// Create the user object with the form data
		if(userForm.isValid()){
			try {
				if(userManager.doModifyUserDetails(principal.getName(), loginId)){
					userManager.modifyUser(userForm, principal.getName(), loginId);
				}
			} catch (SQLException e) {
				logger.error("Issue while getting user details",e);
			}
			
		}
		
		return "redirect:/auth/user/listuser";
	}
}
