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
		
		if(remoteIpAddr == null || challenge == null || response == null){
			return false;
		}
		if(remoteIpAddr.isEmpty() || challenge.isEmpty() || response.isEmpty()){
			return false;
		}
		
		if(response.length() > 20){
			return false;
		}
		
		if(challenge.length() > 500){
			return false;
		}
		
		ReCaptcha reCaptcha = ReCaptchaFactory.newReCaptcha(publickey, 
				privatekey, false);
		
		ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteIpAddr, challenge, response);

		return reCaptchaResponse.isValid();
	}
	
	
	
}
