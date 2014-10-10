package com.asu.alliancebank.recaptcha.impl;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.recaptcha.IReCaptchaManager;

@Service
public class ReCaptchaManager implements IReCaptchaManager{

	@Autowired
	@Qualifier("publickey")
	private String publickey;
	
	
	@Autowired
	@Qualifier("privatekey")
	private String privatekey;
	
	@Override
	public boolean isValid(String remoteIpAddr, String challenge,String response){
		
		ReCaptcha reCaptcha = ReCaptchaFactory.newReCaptcha(publickey, 
				privatekey, false);
		
		ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteIpAddr, challenge, response);

		return reCaptchaResponse.isValid();
	}
	
	
	
}
