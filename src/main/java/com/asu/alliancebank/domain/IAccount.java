/**
 * 
 */
package com.asu.alliancebank.domain;

/**
 * @author Kedar
 *
 */
public interface IAccount {
	public abstract String getAccountID();
	public abstract void setAccountID(String accountID);
	public abstract String getUserID();
	public abstract void setUserID(String userID);
	public abstract long getBalance();
	public abstract void setBalance(long balance);
}
