package com.asu.alliancebank.annotation;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IsEmailValidator implements
		ConstraintValidator<IsEmail, String> {
	private static final Logger logger = LoggerFactory
			.getLogger(NotInvalidStringValidator.class);

	@Override
	public void initialize(IsEmail arg0) {

	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext arg1) {
		boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
	}
}
