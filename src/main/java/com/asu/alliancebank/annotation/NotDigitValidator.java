package com.asu.alliancebank.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotDigitValidator implements
		ConstraintValidator<NotValidDigit, String> {
	private static final Logger logger = LoggerFactory
			.getLogger(NotInvalidStringValidator.class);

	@Override
	public void initialize(NotValidDigit arg0) {

	}

	/**
	 * {@inheritDoc} Checks if the content includes < > " ' % ; ) ( & + -
	 */
	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		if (arg0 == null)
			return false;
		if (arg0.length() > 10) // don't allow to enter more than 50 characters
			return false;
		String invalidStrings[] = { "<", ">", "\"", "'", "%", ";", ")", "(",
				"&", "+", "-", "select", "where", "from", "delete", "update" };
		String temp = arg0.toLowerCase();
		for (String specialChar : invalidStrings) {
			if (temp.contains(specialChar)) {
				return false;
			}
		}
		try {
			Long amount = Long.parseLong(arg0);
			if (amount > 0 && amount <= 100000) {
				return true;
			}
		} catch (NumberFormatException e) {
			return false;
		}

		return false;
	}
}
