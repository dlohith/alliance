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
	public void sendPKIKeys(String loginId,String userId, String emailAddress, String tempPass){
		 String subject = "Private and public for user : "+ loginId + " status : "+ userId;
		 String msgText = "User is created, the following are the details of user:\n User id : "+loginId+" \nTemporary password = "+tempPass+" \n\n"
				 +"Checkout keys in the attachment";
		 String attachments[] = pkiManager.getKeyLocs(loginId);
		 
		 emailSender.sendNotificationEmail(emailAddress, subject, msgText, attachments);
	}
	
	@Override
	public void sendTransactionHash(String transactionId, String emailAddress, String hashvalue){
		String subject = "Hash value for your transaction ID : "+ transactionId;
		 String msgText = "Hash value to be encrypted for your transaction : "+hashvalue;
		 
		 emailSender.sendNotificationEmail(emailAddress, subject, msgText);
	}
	
	@Override
	public void sendOTP(String emailAddress, String otp, String transactionId){
		String subject = "OTP for your transaction ID : "+ transactionId;
		 String msgText = "OTP for your transaction : "+otp;
		 
		 emailSender.sendNotificationEmail(emailAddress, subject, msgText);
	}
}
