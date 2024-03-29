package com.asu.alliancebank.controllers.usermanagement.backingbean;

import java.util.List;

import com.asu.alliancebank.annotation.NotEmpty;
import com.asu.alliancebank.annotation.NotEmptyRoleList;
import com.asu.alliancebank.domain.impl.Role;

public class UserBackingBean {

	@NotEmpty(message = "Please provide a First Name.")
	private String firstName;
	
	@NotEmpty(message = "Please provide a Last Name.")
	private String lastName;
	
	@NotEmpty(message = "Please provide a Login ID.")
	private String loginID;
	
	@NotEmpty(message = "Please provide a Password.")
	private String password;
	
	@NotEmpty(message = "Please provide a repeat Password.")
	private String repeatPassword;
	
	@NotEmpty(message = "Please provide a Email ID.")
	private String emailId;
	
	@NotEmpty(message = "Please provide a Phone Number.")
	private String phoneNo;
	
	@NotEmptyRoleList(message = "Please select atleast one role.")
	private List<Role> roleList;

	public boolean isValid(){
		return true;
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

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
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

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	
	
	
}
