package com.asu.alliancebank.factory;

import java.util.List;

import com.asu.alliancebank.controllers.usermanagement.backingbean.UserBackingBean;
import com.asu.alliancebank.domain.impl.User;
import com.asu.alliancebank.service.userservice.AllianceBankGrantedAuthority;

public interface IUserFactory {

	public User createEmptyUserObject();
	public User createUserInstance(UserBackingBean userBackingBean);
	public User createUserInstance(String firstName, String lastName, 
			String loginID,String password,String emailId, String phoneNo, List<AllianceBankGrantedAuthority> authorities);
}
