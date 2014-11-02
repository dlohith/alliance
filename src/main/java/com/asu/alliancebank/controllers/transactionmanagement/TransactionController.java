package com.asu.alliancebank.controllers.transactionmanagement;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TransactionController {
	
	@RequestMapping(value = "auth/trans", method = RequestMethod.GET)
	public String getToTransactionPage(HttpServletRequest req, ModelMap model, Principal principal) {
		
		String error = req.getParameter("error");
		model.addAttribute("error", error);
		return "auth/trans";
	}
}
