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
public class NotInvalidStringValidator implements ConstraintValidator<NotInvalidString, String>{

	private static final Logger logger = LoggerFactory
			.getLogger(NotInvalidStringValidator.class);
	
	@Override
	public void initialize(NotInvalidString arg0) {
		
	}

	/**
	 * {@inheritDoc}
	 * Checks if the content includes < > " ' % ; ) ( & + -
	 */
	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		if(arg0 == null)
			return false;
		String invalidStrings [] = {"<",">","\"","'","%",";",")","(","&","+","-"};
		for(String specialChar : invalidStrings){
			if(arg0.contains(specialChar)){
				logger.info("String rejected : "+ arg0);
				return false;
			}
		}
		logger.info("String accepted : "+ arg0);
		return true;
	}
}
