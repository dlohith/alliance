/**
 * 
 */
package com.asu.alliancebank.controllers.accountmanagement.backingbean;

import com.asu.alliancebank.annotation.NotEmpty;

/**
 * @author Kedar
 *
 */
public class AccountBackingBean {
	
	@NotEmpty(message = "Please provide a user ID.")
	private String userID;
	
	private Long balance;
	
	private String user;
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public boolean isValid() {
		return true;
	}
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}
	

}
