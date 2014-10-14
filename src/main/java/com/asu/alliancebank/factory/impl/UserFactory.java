package com.asu.alliancebank.factory.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.controllers.usermanagement.backingbean.UserBackingBean;
import com.asu.alliancebank.domain.impl.Role;
import com.asu.alliancebank.domain.impl.User;
import com.asu.alliancebank.factory.IUserFactory;
import com.asu.alliancebank.service.userservice.AllianceBankGrantedAuthority;

@Service
public class UserFactory implements IUserFactory{

	@Override
	public User createUserInstance(UserBackingBean userBackingBean){
		
		String encryptedPasswd = encrypt(userBackingBean.getPassword());
		
		List<AllianceBankGrantedAuthority> authorities = new ArrayList<AllianceBankGrantedAuthority>();
		
		if(userBackingBean.getRoleList() != null){
			for(Role role : userBackingBean.getRoleList()){
				authorities.add(new AllianceBankGrantedAuthority(role.getName()));
			}
		}
		
		return new User(userBackingBean.getEmailId(),
				userBackingBean.getLastName(),
				userBackingBean.getLoginID(),
				encryptedPasswd,
				userBackingBean.getEmailId(),
				userBackingBean.getPhoneNo(),
				authorities);
	}
	
	public String encrypt(String pw) {
		return BCrypt.hashpw(pw, BCrypt.gensalt());
	}
}
