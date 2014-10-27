package com.asu.alliancebank.security.checkcapability;

import org.springframework.security.core.Authentication;

public interface ICheckUserRoleCapability {

	public boolean isAdminRole(Authentication auth);
}
