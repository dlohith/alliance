/**
 * 
 */
package com.asu.alliancebank.controllers.accountmanagement.backingbean;

import com.asu.alliancebank.annotation.NotEmpty;
import com.asu.alliancebank.annotation.NotInvalidString;

/**
 * @author Kedar
 *
 */
public class AccountBackingBean {
	
	@NotEmpty(message = "Please provide a user ID.")
	@NotInvalidString(message = "Please check the user ID you have given - either its too big or it has invalid content")
	private String userID;
	
	private Long balance;
	
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
