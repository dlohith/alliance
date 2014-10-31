package com.asu.alliancebank.service.email.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.email.EmailSender;
import com.asu.alliancebank.security.pki.impl.PKIManager;
import com.asu.alliancebank.service.email.IEmailManagement;

@Service
public class EmailManagement implements IEmailManagement {

	@Autowired
	private EmailSender emailSender;
	
	@Autowired
	private PKIManager pkiManager;
	
	@Override
	public void sendPKIKeys(String loginId, String emailAddress){
		 String subject = "Private and public for user : "+ loginId;
		 String msgText = "Checkout keys in the attachment";
		 String attachments[] = pkiManager.getKeyLocs(loginId);
		 
		 emailSender.sendNotificationEmail(emailAddress, subject, msgText, attachments);
	}
}
