package com.asu.alliancebank.recaptcha;

public interface IReCaptchaManager {

	public boolean isValid(String remoteIpAddr, String challenge, String response);

}
