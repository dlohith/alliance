package com.asu.alliancebank.service.role;

import java.util.List;

import com.asu.alliancebank.domain.impl.Role;

public interface IRoleManager {

	public static final String ROLE_SYSTEM_ADMIN = "ROLE_SYSTEM_ADMIN";
	public static final String ROLE_INDIVIDUAL_CUSTOMER = "ROLE_INDIVIDUAL_CUSTOMER";
	
	
	public abstract Role[] getRoles();

	public abstract Role getRole(String id);

	public abstract List<Role> getRolesList();

	public List<Role> getRoleList(String roleIds);
}
