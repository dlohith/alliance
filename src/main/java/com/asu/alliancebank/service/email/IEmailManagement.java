package com.asu.alliancebank.service.email;

public interface IEmailManagement {

	public void sendPKIKeys(String loginId,String userId, String emailAddress, String tempPass);
	public void sendTransactionHash(String transactionId, String emailAddress, String hashvalue);
	public void sendOTP(String email, String otp, String transactionId);
}
