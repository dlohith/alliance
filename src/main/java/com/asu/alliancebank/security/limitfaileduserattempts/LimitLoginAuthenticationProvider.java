package com.asu.alliancebank.security.limitfaileduserattempts;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.asu.alliancebank.service.user.IUserManager;


public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider {

	private static final Logger logger = LoggerFactory
			.getLogger(LimitLoginAuthenticationProvider.class);
	
	
	@Autowired
	private IUserManager userManager;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		try {

			Authentication auth = super.authenticate(authentication);
			try {
				logger.info("Correct password for "+authentication.getName());
				userManager.resetFailAttempts(authentication.getName());
			} catch (SQLException e) {

			}

			return auth;

		} catch (BadCredentialsException e) {

			// invalid login, update to user_attempts
			try {
				logger.info("Wrong password for "+authentication.getName());
				userManager.updateFailedLoginAttempts(authentication.getName());
			} catch (SQLException e1) {

			}
			throw e;

		} catch (LockedException e) {

			String error = "User is locked";
			throw new LockedException(error);
		}

	}

}
