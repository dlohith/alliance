package com.asu.alliancebank.service.role;

import java.util.List;

import com.asu.alliancebank.domain.IRole;

public interface IRoleManager {

	public abstract IRole[] getRoles();

	public abstract IRole getRole(String id);

	public abstract List<IRole> getRolesList();

}