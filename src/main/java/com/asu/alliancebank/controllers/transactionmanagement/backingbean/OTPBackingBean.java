package com.asu.alliancebank.controllers.transactionmanagement.backingbean;

import com.asu.alliancebank.annotation.NotEmpty;

public class OTPBackingBean {

	@NotEmpty(message = "Please provide to otp")
	private String otp;

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	
}
