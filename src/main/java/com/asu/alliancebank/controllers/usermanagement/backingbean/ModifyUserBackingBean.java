package com.asu.alliancebank.controllers.usermanagement.backingbean;

import java.util.List;

import com.asu.alliancebank.annotation.NotEmpty;
import com.asu.alliancebank.annotation.NotEmptyRoleList;
import com.asu.alliancebank.annotation.NotInvalidString;
import com.asu.alliancebank.domain.impl.Role;

public class ModifyUserBackingBean {


	@NotEmpty(message = "Please provide a First Name.")
	@NotInvalidString(message = "Please enter valid characters. Characters like < > \" ' % ; ) ( & + - not allowed. ")
	private String firstName;
	
	@NotEmpty(message = "Please provide a Last Name.")
	@NotInvalidString(message = "Please enter valid characters. Characters like < > \" ' % ; ) ( & + - not allowed. ")
	private String lastName;
	
	
	@NotEmpty(message = "Please provide a Email ID.")
	@NotInvalidString(message = "Please enter valid characters. Characters like < > \" ' % ; ) ( & + - not allowed. ")
	private String emailId;
	
	@NotEmpty(message = "Please provide a Phone Number.")
	@NotInvalidString(message = "Please enter valid characters. Characters like < > \" ' % ; ) ( & + - not allowed. ")
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