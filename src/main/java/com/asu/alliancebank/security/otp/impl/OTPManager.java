package com.asu.alliancebank.security.otp.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class OTPManager {

	
	public int getOTP(){
		Random rnd = new Random();
		int n = 100000 + rnd.nextInt(900000);
		return n;
	}
}
