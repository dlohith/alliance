package com.asu.alliancebank.security.checkcapability.impl;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.security.checkcapability.ICheckUserRoleCapability;
import com.asu.alliancebank.service.role.IRoleManager;

@Service
public class CheckUserRoleCapability implements ICheckUserRoleCapability{
	
	
	/**
	 * call this by passing following in controller
	 * auth = SecurityContextHolder.getContext().getAuthentication();
	 * 
	 */
	@Override
	public boolean isAdminRole(Authentication auth){
		if(auth == null)
			return false;
		
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		boolean access = false; 
		for (GrantedAuthority ga : authorities) {
			if(ga.getAuthority().equals(IRoleManager.ROLE_SYSTEM_ADMIN))
				access=true;
		}
		return access;
	}

	@Override
	public boolean isIndividualRole(Authentication auth){
		if(auth == null)
			return false;
		
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		boolean access = false; 
		for (GrantedAuthority ga : authorities) {
			if(ga.getAuthority().equals(IRoleManager.ROLE_INDIVIDUAL_CUSTOMER))
				access=true;
		}
		return access;
	}

	@Override
	public boolean isMerchantRole(Authentication auth) {
		if(auth == null)
			return false;
		
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		boolean access = false; 
		for (GrantedAuthority ga : authorities) {
			if(ga.getAuthority().equals(IRoleManager.ROLE_MERCHANT))
				access=true;
		}
		return access;
	}

	@Override
	public boolean isBankEmployeeRole(Authentication auth) {
		if(auth == null)
			return false;
		
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		boolean access = false; 
		for (GrantedAuthority ga : authorities) {
			if(ga.getAuthority().equals(IRoleManager.ROLE_BANK_EMPLOYEE))
				access=true;
		}
		return access;
	}
}
