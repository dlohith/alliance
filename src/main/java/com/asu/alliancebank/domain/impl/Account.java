/**
 * 
 */
package com.asu.alliancebank.domain.impl;

import com.asu.alliancebank.domain.IAccount;

/**
 * @author Kedar
 *
 */
public class Account implements IAccount {

	private String accountID;
	private String userID;
	private Long   balance;
	
	public Account() {	
	}
	
	public Account(String userID, Long balance) {
		this.userID = userID;
		this.balance = balance;
		this.balance = new Long(0);
	}
	
	@Override
	public String getAccountID() {
		return accountID;
	}

	@Override
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	@Override
	public String getUserID() {
		return userID;
	}

	@Override
	public void setUserID(String userID) {
		this.userID = userID;
	}

	@Override
	public long getBalance() {
		return balance;
	}

	@Override
	public void setBalance(long balance) {
		this.balance = balance;
	}
}
