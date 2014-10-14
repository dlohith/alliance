package com.asu.alliancebank.service.role;

import java.util.List;

import com.asu.alliancebank.domain.impl.Role;

public interface IRoleManager {

	public abstract Role[] getRoles();

	public abstract Role getRole(String id);

	public abstract List<Role> getRolesList();

}