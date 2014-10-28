package com.asu.alliancebank.annotation;

import java.sql.SQLException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.asu.alliancebank.service.user.IUserManager;

public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {

	private static final Logger logger = LoggerFactory
			.getLogger(UniqueLoginValidator.class);
	
	@Autowired
	private IUserManager userManager;
	
	@Override
	public void initialize(UniqueLogin arg0) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid(String loginId, ConstraintValidatorContext arg1) {
		
		if(loginId == null)
			return false;
		try {
			boolean test = userManager.isLoginIdUnique(loginId);
			logger.info("is login unique :  "+test);
			return test;
		} catch (SQLException e) {
			logger.error("DB Issue ",e);
			return false;
		} 
	}
}
