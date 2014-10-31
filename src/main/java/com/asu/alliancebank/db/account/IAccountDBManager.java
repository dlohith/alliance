/**
 * 
 */
package com.asu.alliancebank.db.account;

import java.sql.SQLException;
import java.util.List;

import com.asu.alliancebank.domain.impl.Account;

/**
 * @author Kedar
 *
 */
public interface IAccountDBManager {
	public String addAccount(Account account, String loggedInUser) throws SQLException;
	public Account getAccount(String accountID) throws SQLException;
	public String deleteAccount(String accountID) throws SQLException;
	public List<Account> getAllAccounts() throws SQLException;
	public List<String> getUserLoginIDsForListAccount(String loggedInUser) throws SQLException;
	public String getAccountBalance(String loggedInUser) throws SQLException;
}
