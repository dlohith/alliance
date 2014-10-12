package com.asu.alliancebank;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asu.alliancebank.recaptcha.IReCaptchaManager;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private IReCaptchaManager reCaptchaManager;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/testing", method = RequestMethod.POST)
	public String getCaptcha(HttpServletRequest req,HttpServletResponse res, Model model) {
		
		String response = req.getParameter("recaptcha_response_field");
		String challenge = req.getParameter("recaptcha_challenge_field");
		String remoteIpAddr = req.getHeader("X-FORWARDED-FOR");  
		   if (remoteIpAddr == null) {  
			   remoteIpAddr = req.getRemoteAddr();  
		   }
		   
		   System.out.println("Output : "+ reCaptchaManager.isValid(remoteIpAddr, challenge, response));;

		return "test";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/auth/welcome", method = RequestMethod.GET)
	public String home(Locale locale, Model model,Principal principal) {
		
		String userName = principal.getName(); 
		logger.info("username : {}.", userName);
		model.addAttribute("username", userName );
		
		return "auth/welcome";
	}
	
}
