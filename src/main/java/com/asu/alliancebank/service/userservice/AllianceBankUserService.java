package com.asu.alliancebank.service.userservice;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *  Customized {@link UserDetailsService} class to get user details and 
 * authenticate it based on the User and encoded Password
 * @author : Lohith Dwaraka 
 *
 */
@Service(value = "allianceBankUserService")
public class AllianceBankUserService implements UserDetailsService{

	@Override
	public synchronized UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
