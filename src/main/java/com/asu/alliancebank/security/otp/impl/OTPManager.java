package com.asu.alliancebank.security.otp.impl;

import java.sql.SQLException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.domain.impl.User;
import com.asu.alliancebank.service.email.IEmailManagement;
import com.asu.alliancebank.service.user.IUserManager;

@Service
public class OTPManager {
	
	@Autowired
	private IUserManager userManager;
	
	@Autowired
	private IEmailManagement emailManagement;
	
	
	public int getOTP(){
		Random rnd = new Random();
		int n = 100000 + rnd.nextInt(900000);
		return n;
	}
	
	public void transferFundsOtpActions(String transactionId, String loggedInUser) throws SQLException{
		String otp = getOTP()+"";
		attachOTPWithTransaction(transactionId, otp, loggedInUser);
		User user = userManager.getUserDetails(loggedInUser);
		
		if(user != null){
			sendOtpasEmail(user.getEmailId(), otp, transactionId);
		}
	}
	
	private void attachOTPWithTransaction(String transactionId, String otp, String loggedInUser){
		
	}
	
	private void sendOtpasEmail(String emailId, String otp, String transactionId){
		emailManagement.sendOTP(emailId, otp, transactionId);
	}
	
	
	
	
}
