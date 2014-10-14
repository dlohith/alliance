package com.asu.alliancebank.domain.impl;

import java.util.ArrayList;
import java.util.List;

import com.asu.alliancebank.service.userservice.AllianceBankGrantedAuthority;

public class User {
	
	private String firstName;
	private String lastName;
	private String loginID;
	private String password;
	private String emailId;
	private String phoneNo;
	private List<AllianceBankGrantedAuthority> authorities;
	
	public User(String firstName, String lastName, 
			String loginID,String password,String emailId, String phoneNo, List<AllianceBankGrantedAuthority> authorities){
		this.firstName = firstName;
		this.lastName = lastName;
		this.loginID = loginID;
		this.password = password;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.authorities = authorities;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLoginID() {
		return loginID;
	}
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public List<AllianceBankGrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<AllianceBankGrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void addAuthorities(String roleName){
		if(authorities == null){
			authorities = new ArrayList<AllianceBankGrantedAuthority>();
		}
		authorities.add(new AllianceBankGrantedAuthority(roleName));
		
	}

	
	
}
