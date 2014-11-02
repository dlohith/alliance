package com.asu.alliancebank.security.checkcapability;

import org.springframework.security.core.Authentication;

public interface ICheckUserRoleCapability {

	public boolean isAdminRole(Authentication auth);
	public boolean isIndividualRole(Authentication auth);
	public boolean isMerchantRole(Authentication auth);
	public boolean isBankEmployeeRole(Authentication auth);
}
