package com.asu.alliancebank.security.otp.impl;

import java.sql.SQLException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.controllers.transactionmanagement.backingbean.OTPBackingBean;
import com.asu.alliancebank.db.transaction.ITransactionOTPDBManager;
import com.asu.alliancebank.domain.impl.TransferFunds;
import com.asu.alliancebank.domain.impl.User;
import com.asu.alliancebank.service.email.IEmailManagement;
import com.asu.alliancebank.service.user.IUserManager;

@Service
public class OTPManager {
	
	@Autowired
	private IUserManager userManager;
	
	@Autowired
	private IEmailManagement emailManagement;
	
	@Autowired
	@Qualifier("TransactionOTPDBManager")
	private ITransactionOTPDBManager dbConnect;
	
	
	public int getOTP(){
		Random rnd = new Random();
		int n = 100000 + rnd.nextInt(900000);
		return n;
	}

	
	public void sendOtpasEmail(String otp, String transactionId, String loggedInUser) throws SQLException{
		User user = userManager.getUserDetails(loggedInUser);
		
		if(user != null){
			emailManagement.sendOTP(user.getEmailId(), otp, transactionId);
		}
		
	}
	
	
	public boolean isTransactionOtpCorrect(OTPBackingBean otpBackingBean, String transactionId) throws SQLException{
		return dbConnect.isTransactionOtpCorrect(transactionId, otpBackingBean.getOtp());
	}
	
	public TransferFunds getTransferFunds(String transactionId,String loggedInUser) throws SQLException{
		return dbConnect.getTransferFunds(transactionId, loggedInUser);
	}
	
	public String updateTransaction(String transactionId,TransferFunds transferFunds, String loggedInUser, String otp) throws SQLException{
		return dbConnect.updateTransferFunds(transactionId,transferFunds, loggedInUser, otp);
	}
	
	
}
