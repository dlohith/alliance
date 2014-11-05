package com.asu.alliancebank.security.password;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class RandomPasswordGenerator {

	private final int RAMDOM_PASSWORD_LENGTH = 14;

	public String generateRandomPassword() {
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890+@";

		Random r = new Random();
		StringBuffer password = new StringBuffer();
		for (int i = 0; i < RAMDOM_PASSWORD_LENGTH; i++) {
			int index = r.nextInt(letters.length());
			password.append(letters.charAt(index));
		}
		return password.toString();
	}

}
