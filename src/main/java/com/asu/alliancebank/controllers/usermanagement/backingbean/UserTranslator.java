package com.asu.alliancebank.controllers.usermanagement.backingbean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.domain.impl.Role;
import com.asu.alliancebank.domain.impl.User;
import com.asu.alliancebank.service.role.IRoleManager;
import com.asu.alliancebank.service.userservice.AllianceBankGrantedAuthority;

@Service
public class UserTranslator {

	@Autowired
	private IRoleManager roleManager;
	
	public ModifyUserBackingBean convertUserToModifyUserBackingBean(User user){
		ModifyUserBackingBean modifyUserBackingBean =  new ModifyUserBackingBean();
		
		modifyUserBackingBean.setEmailId( user.getEmailId());
		modifyUserBackingBean.setFirstName ( user.getFirstName());
		modifyUserBackingBean.setLastName( user.getLastName());
		modifyUserBackingBean.setPhoneNo(user.getPhoneNo());
		
		List<Role> roles = new ArrayList<Role>();
		for(AllianceBankGrantedAuthority authority : user.getAuthorities()){
			Role role = roleManager.getRole(authority.getAuthority());
			if(role != null){
				roles.add(roleManager.getRole(authority.getAuthority()));
			}
		}
		
		modifyUserBackingBean.setRoleList(roles);
		
		return modifyUserBackingBean;
		
	}
}

