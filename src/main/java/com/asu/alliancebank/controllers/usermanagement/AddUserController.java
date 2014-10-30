package com.asu.alliancebank.controllers.usermanagement;

import java.security.Principal;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
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

import com.asu.alliancebank.controllers.usermanagement.backingbean.UserBackingBean;
import com.asu.alliancebank.domain.impl.User;
import com.asu.alliancebank.factory.IUserFactory;
import com.asu.alliancebank.recaptcha.IReCaptchaManager;
import com.asu.alliancebank.service.role.IRoleManager;
import com.asu.alliancebank.service.user.IUserManager;

/**
 * Add User controller functions
 * 
 * @author Lohith
 *
 */
@Controller
public class AddUserController {

	@Autowired
	private IRoleManager roleManager;
	
	@Autowired
	private IUserManager userManager;
	
	@Autowired
	private IUserFactory userFactory;
	
	@Autowired
	private IReCaptchaManager reCaptchaManager;
	
	private static final Logger logger = LoggerFactory
			.getLogger(AddUserController.class);
	
	/**
	 * Helps create add {@link User} form by getting the object ready for form jsp tags
	 * @param model ModelMap object for this request
	 * @param principal User related details are access using this
	 * @return Prepares the Page for AddUser form
	 */
	@RequestMapping(value = "auth/user/adduser", method = RequestMethod.GET)
	public String getToAddUserPage( ModelMap model, Principal principal) {
		
		// If user is authorized
		model.addAttribute("availableRoles", roleManager.getRolesList());
		model.addAttribute("userBackingBean", new UserBackingBean());
		return "auth/user/adduser";
	}
	
	
	/**
	 * Adds users by receiving the {@link User} details as object form
	 * @param userForm This is a {@link UserBackingBean} used to get data from front end form
	 * @param result {@link BindingResult} to check binding error
	 * @param map {@link ModelMap} for this request
	 * @return send the String for apache tiles to decide on the view 
	 */
	@RequestMapping(value = "auth/user/adduser", method = RequestMethod.POST)
	public String addNewUser(HttpServletRequest req, @Valid @ModelAttribute UserBackingBean userForm, BindingResult result, ModelMap map, Principal principal) {

		// If user data has validation issues
		if (result.hasErrors()) {
			map.addAttribute("availableRoles", roleManager.getRoles());
			return "auth/user/adduser";
		}		
		
		String response = req.getParameter("recaptcha_response_field");
		String challenge = req.getParameter("recaptcha_challenge_field");
		
		if(response == null || response.isEmpty()){
			map.addAttribute("captchaError","Captcha empty");
			map.addAttribute("availableRoles", roleManager.getRoles());
			return "auth/user/adduser";
		}
		if(challenge == null || challenge.isEmpty()){
			map.addAttribute("captchaError","Some one removed this challenge field");
			map.addAttribute("availableRoles", roleManager.getRoles());
			return "auth/user/adduser";
		}
		
		String remoteIpAddr = req.getHeader("X-FORWARDED-FOR");  
		if (remoteIpAddr == null) {  
			remoteIpAddr = req.getRemoteAddr();  
		}
		
		if(!reCaptchaManager.isValid(remoteIpAddr, challenge, response)){
			map.addAttribute("captchaError","Wrong captcha input");
			map.addAttribute("availableRoles", roleManager.getRoles());
			return "auth/user/adduser";
		}
		
		// Create the user object with the form data
		if(userForm.isValid()){

			User user = userFactory.createUserInstance(userForm);
			try {
				userManager.addUser(user,principal.getName() );
			} catch (SQLException e) {
				logger.error("Error while adding user",e);
			}
		}
		
		return "redirect:/auth/user/listuser";
	}
	
	
}
