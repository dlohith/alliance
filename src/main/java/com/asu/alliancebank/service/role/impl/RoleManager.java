package com.asu.alliancebank.service.role.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.domain.IRole;
import com.asu.alliancebank.domain.impl.Role;
import com.asu.alliancebank.service.role.IRoleManager;

@Service
public class RoleManager implements IRoleManager {

	@Autowired
	private List<IRole> roles;
	

	@Override
	public IRole[] getRoles() {
		return roles.toArray(new Role[roles.size()]);
	}
	

	@Override
	public IRole getRole(String id) {
		for (IRole role : roles) {
			if (role.getId().equals(id))
				return role;
		}
		return null;
	}


	@Override
	public List<IRole> getRolesList() {
		return this.roles;
	}
}
