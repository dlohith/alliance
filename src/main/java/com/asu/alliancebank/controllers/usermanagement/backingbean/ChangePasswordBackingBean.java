package com.asu.alliancebank.controllers.usermanagement.backingbean;

import com.asu.alliancebank.annotation.NotEmpty;
import com.asu.alliancebank.annotation.NotInvalidString;

public class ChangePasswordBackingBean {
	
	@NotEmpty(message = "Please provide a login ID.")
	@NotInvalidString(message = "Please enter valid characters. Characters like < > \" ' % ; ) ( & + - not allowed. ")
	private String loginId;
	
	@NotEmpty(message = "Please provide a old password.")
	@NotInvalidString(message = "Please enter valid characters. Characters like < > \" ' % ; ) ( & + - not allowed. ")
	private String oldPassword;

	@NotEmpty(message = "Please provide a new password.")
	@NotInvalidString(message = "Please enter valid characters. Characters like < > \" ' % ; ) ( & + - not allowed. ")
	private String newPassword;
	
	@NotEmpty(message = "Please provide a repeat new password.")
	@NotInvalidString(message = "Please enter valid characters. Characters like < > \" ' % ; ) ( & + - not allowed. ")
	private String repeatNewPassword;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRepeatNewPassword() {
		return repeatNewPassword;
	}

	public void setRepeatNewPassword(String repeatNewPassword) {
		this.repeatNewPassword = repeatNewPassword;
	}

	
	public boolean isValid(){
		if(newPassword.equals(repeatNewPassword)){
			return true;
		}
		return false;
	}

}
