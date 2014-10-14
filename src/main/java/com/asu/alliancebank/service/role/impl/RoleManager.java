package com.asu.alliancebank.service.role.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.domain.impl.Role;
import com.asu.alliancebank.service.role.IRoleManager;

@Service
public class RoleManager implements IRoleManager {

	@Autowired
	private List<Role> roles;
	

	@Override
	public Role[] getRoles() {
		return roles.toArray(new Role[roles.size()]);
	}
	

	@Override
	public Role getRole(String id) {
		for (Role role : roles) {
			if (role.getId().equals(id))
				return role;
		}
		return null;
	}


	@Override
	public List<Role> getRolesList() {
		return this.roles;
	}
}
