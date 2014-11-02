/**
 * 
 */
package com.asu.alliancebank.service.account;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.asu.alliancebank.domain.impl.Account;

/**
 * @author Kedar
 *
 */
public interface IAccountManager {
	public abstract void addAccount(Account account, String loggedInUser) throws SQLException;
	public abstract Account getAccount(String accountID) throws SQLException;
	public abstract void deleteAccount(String accountID) throws SQLException;
	public abstract void deleteAccounts(List<String> accountIDs) throws SQLException;
	public abstract List<Account> listAllAccounts(String loggedInUser) throws SQLException;
	public abstract List<String> listAllUserLoginIdsFromAccounts(String loggedInUser) throws SQLException;
	public abstract Map<String, String> getUserListForAddAccount(String loggedInUser); 
	public abstract List<String> checkDeleteAccountUserIDs(String userLoginIDs[],String loggedInUser)throws SQLException;
	public abstract Long getAccountBalance(String loggedInUser) throws SQLException;
	public abstract boolean isValidUserLoginID(List<Account> accountList, String userLoginID);
}
