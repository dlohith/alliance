package com.asu.alliancebank.service.userservice;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.service.user.IUserManager;

/**
 *  Customized {@link UserDetailsService} class to get user details and 
 * authenticate it based on the User and encoded Password
 * @author : Lohith Dwaraka 
 *
 */
@Service(value = "allianceBankUserService")
public class AllianceBankUserService implements UserDetailsService{

	@Autowired
	private IUserManager userManager;
	
	private static final Logger logger = LoggerFactory
			.getLogger(AllianceBankUserService.class);
	
	@Override
	public synchronized UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		try {
			UserDetails matchingUser = userManager.getUserDetails(username);
			if(matchingUser == null){
				throw new UsernameNotFoundException("Wrong username or password");
			}
			return matchingUser;
		} catch (SQLException e) {
			logger.error("Error fetching matching user from DB",e);
		}

		return null;
	}

}
