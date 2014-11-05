package com.asu.alliancebank.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotValidPhoneNumber implements ConstraintValidator<NotValidInteger, String>{
	
		private static final Logger logger = LoggerFactory
				.getLogger(NotValidPhoneNumber.class);

		@Override
		public void initialize(NotValidInteger arg0) {

		}

		/**
		 * {@inheritDoc} Checks if the content includes only numbers.
		 */
		@Override
		public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
			if (arg0 == null)
				return false;
			if (arg0.length() > 10) // don't allow to enter more than 50 characters
				return false;
			String regex = "[0-9]+"; 									
			try {				
				if (arg0.matches(regex)) {
					return true;
				}
			} catch (NumberFormatException e) {
				return false;
			}

			return false;
		}
	}

