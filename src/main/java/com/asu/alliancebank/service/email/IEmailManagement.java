package com.asu.alliancebank.service.email;

public interface IEmailManagement {

	public void sendPKIKeys(String loginId, String emailAddress, String tempPass);
	public void sendOTP(String email, String otp, String transactionId);
}
