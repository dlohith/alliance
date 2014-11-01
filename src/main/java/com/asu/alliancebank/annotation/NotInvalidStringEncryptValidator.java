package com.asu.alliancebank.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class works as a validator for NotInvalidString annotation
 * 
 * @author : Lohith Dwaraka
 *
 */
public class NotInvalidStringEncryptValidator implements ConstraintValidator<NotInvalidEncryptString, String>{

	private static final Logger logger = LoggerFactory
			.getLogger(NotInvalidStringEncryptValidator.class);
	
	@Override
	public void initialize(NotInvalidEncryptString arg0) {
		
	}

	/**
	 * {@inheritDoc}
	 * Checks if the content includes < > " ' % ; ) ( & + -
	 */
	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		if(arg0 == null)
			return false;
		if(arg0.length() > 500)	// don't allow to enter more than 50 characters
			return false;
		String invalidStrings [] = {"<",">","\"","'","%",";",")","(","&","+","-","select",
									 "where", "from", "delete", "update"};
		String temp = arg0.toLowerCase();
		for(String specialChar : invalidStrings){
			if(temp.contains(specialChar)){
				return false;
			}
		}
		return true;
	}
}
