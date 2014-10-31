package com.asu.alliancebank.controllers.transactionmanagement;

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

import com.asu.alliancebank.domain.impl.Transaction;
import com.asu.alliancebank.service.transaction.ITransactionManager;

@Controller
public class ListTransactionLogsController {	
	
	@Autowired
	private ITransactionManager transactionManager;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ListTransactionLogsController.class);
	
	/**
	 * Helps create add {@link Transaction} form by getting the object ready for form jsp tags
	 * @param model ModelMap object for this request
	 * @param principal Transaction related details are access using this
	 * @return Prepares the Page for AddTransaction form
	 */
	@RequestMapping(value = "auth/trans/transactionLogsList", method = RequestMethod.GET)
	public String getTransactionLogs( ModelMap model, Principal principal) {	
		if(principal != null){
			try {
				List<Transaction> transaction = transactionManager.listAllTransactionLogs(principal.getName());
				model.addAttribute("transactionLogList", transaction);
				
			} catch (SQLException e) {
				logger.error("Issue fetching transaction list from DB",e);
				
			}
		}
		return "auth/trans/transactionLogsList";		
	}
	
	
}
