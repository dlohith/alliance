package com.asu.alliancebank.controllers;

import java.security.Principal;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asu.alliancebank.recaptcha.IReCaptchaManager;
import com.asu.alliancebank.security.pki.impl.PKIManager;

/**
 * Handles requests for the application home page.
 * 
 * @author Lohith
 */
@Controller
public class HomeController {
	
	@Autowired
	private IReCaptchaManager reCaptchaManager;
	
	@Autowired
	private PKIManager pkiManager;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Default method for web application
	 * This method would direct to appropriate page based on the user login status
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, Principal principal) {
		if(principal == null){
			return "redirect:/login";
		}else{
			return "redirect:/auth/welcome";
		}
	}
	
	/**
	 * Testing purpose don't delete
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/testing", method = RequestMethod.POST)
	public String getCaptcha(HttpServletRequest req,HttpServletResponse res, Model model) {
		
		String response = req.getParameter("recaptcha_response_field");
		String challenge = req.getParameter("recaptcha_challenge_field");
		String remoteIpAddr = req.getHeader("X-FORWARDED-FOR");  
		   if (remoteIpAddr == null) {  
			   remoteIpAddr = req.getRemoteAddr();  
		   }
		   
		   logger.info("Output : "+ reCaptchaManager.isValid(remoteIpAddr, challenge, response));;

		return "test";
	}
	
	
	@RequestMapping(value = "/auth/testpki", method = RequestMethod.POST)
	public String getPkiVerification(HttpServletRequest req,HttpServletResponse res, Principal principal, Model model) {
		
		String response = req.getParameter("test");
		
		if(pkiManager.isResponseValid(response,principal.getName() )){
			logger.info("Success");
		}else{
			logger.info("Fail");
		}

		return "redirect:/auth/welcome";
	}
	
}
