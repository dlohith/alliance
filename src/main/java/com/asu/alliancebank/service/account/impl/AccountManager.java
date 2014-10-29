/**
 * 
 */
package com.asu.alliancebank.service.account.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.db.account.IAccountDBManager;
import com.asu.alliancebank.domain.impl.Account;
import com.asu.alliancebank.domain.impl.User;
import com.asu.alliancebank.factory.IAccountFactory;
import com.asu.alliancebank.service.account.IAccountManager;
import com.asu.alliancebank.service.user.IUserManager;

/**
 * @author Kedar
 *
 */
@Service
public class AccountManager implements IAccountManager {
	
	@Autowired
	@Qualifier("AccountDBManagerBean")
	private IAccountDBManager dbConnect;
	
	@Autowired
	private IUserManager userManager;
	
	@Autowired
	private IAccountFactory accountFactory;
	
	private static final Logger logger = LoggerFactory
			.getLogger(AccountManager.class);
	

	@Override
	public void addAccount(Account account, String loggedInUser)
			throws SQLException {
		if(account != null) {
			dbConnect.addAccount(account, loggedInUser);
		}
	}

	@Override
	public Account getAccount(String accountID) throws SQLException {
		Account account = null;
		if(accountID != null) {
			account = dbConnect.getAccount(accountID);
		}
		return account;
	}

	@Override
	public void deleteAccount(String accountID) throws SQLException {
		if(accountID != null) {
			dbConnect.deleteAccount(accountID);
		}
	}
	

	@Override
	/**
	 * Check if there is no tampering with user ids through UI
	 */
	public Map<String, String> getUserListForAddAccount(String loggedInUser){
		Map<String, String> userIDs = new LinkedHashMap<String, String>();
		List<User> userList = null;
		try {
			userList = userManager.listAllUser(loggedInUser);
			List<Account> accountList = dbConnect.getAllAccounts();
			Map<String, String> accountUserMap = getAccountUserMap(accountList);
			for(User user : userList) {
				if(!accountUserMap.containsKey(user.getLoginID())) {
					userIDs.put(user.getLoginID(), user.getLoginID());
				}	
			}
		} catch (SQLException e) {
			logger.error("Error while getting user list " + e);
		}
		return userIDs;
	}

	private Map<String, String> getAccountUserMap(List<Account> accountList) {
		Map<String, String> accountUserMap = new LinkedHashMap<String, String>();
		for(Account account : accountList) {
			accountUserMap.put(account.getUserID(), account.getAccountID());
		}
		return accountUserMap;
	}

	@Override
	public List<Account> listAllAccounts(String loggedInUser)
			throws SQLException {
		List<Account> accounts = null;
		if(loggedInUser != null) {
			accounts = dbConnect.getAllAccounts();
		}
		return accounts;
	}
	
	@Override
	public List<String> listAllUserLoginIdsFromAccounts(String loggedInUser) {
		List<String> loginIDList = null;
		if(loggedInUser != null) {
			try {
				loginIDList	= dbConnect.getUserLoginIDsForListAccount(loggedInUser);
			} catch (SQLException e) {
				logger.error("DB Issue");
				logger.error("Error while fetching login id list - " + e);
			}
		}
		return loginIDList;
	}

	/**
	 * Checks and return appropriate user ids to delete.
	 * To avoid unwanted account delete
	 */
	@Override
	public List<String> checkDeleteAccountUserIDs(String[] userLoginIDs, String loggedInUser) throws SQLException {
		List<Account> accounts = dbConnect.getAllAccounts();
		Map<String, Account> userMap = convertListOfAccountsToMap(accounts);
		List<String> accountIDsMarkedForDeletetion = new ArrayList<String>();
		for(int i=0 ;i<userLoginIDs.length;i++){
			if(userMap.containsKey(userLoginIDs[i])){
				String accountID = userMap.get(userLoginIDs[i]).getAccountID();
				accountIDsMarkedForDeletetion.add(accountID);
			}
		}
		return accountIDsMarkedForDeletetion;
	}

	private Map<String, Account> convertListOfAccountsToMap(List<Account> accounts) {
		Map<String, Account> accountMap = new LinkedHashMap<String, Account>();
		for(Account account : accounts) {
			accountMap.put(account.getUserID(), account);
		}
		return accountMap;
	}

	@Override
	public void deleteAccounts(List<String> accountIDs) throws SQLException {
		for(String accountID : accountIDs) {
			dbConnect.deleteAccount(accountID);
		}
	}
}
