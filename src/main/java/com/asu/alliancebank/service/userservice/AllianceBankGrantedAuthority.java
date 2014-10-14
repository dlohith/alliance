package com.asu.alliancebank.service.userservice;

import org.springframework.security.core.GrantedAuthority;

import com.asu.alliancebank.domain.impl.Role;

public class AllianceBankGrantedAuthority implements GrantedAuthority{

	private String roleName;
	private static final long serialVersionUID = 711167440813692597L;
	
	public AllianceBankGrantedAuthority(){
		super();
	}
	/**
	 * Sets the rolename for this object
	 * @param name
	 */
	public AllianceBankGrantedAuthority(String name){
		this.roleName=name;
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public String getAuthority() {
		return this.roleName;
	}

	/**
	 * Setter for the {@link Role}
	 * @param roleName
	 */
	public void setAuthority(String roleName) {
		this.roleName = roleName;
	}
	
	
}
