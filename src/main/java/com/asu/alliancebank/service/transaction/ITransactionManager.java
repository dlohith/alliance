package com.asu.alliancebank.service.transaction;

import java.sql.SQLException;
import java.util.List;

import com.asu.alliancebank.domain.impl.Transaction;

public interface ITransactionManager {
	
	public static final int PENDING = 1;
	public static final int SUCCESS = 2;
	public static final int FAILURE = 3;
	
	
	public static final String CREDIT ="CREDIT"; 
	public static final String DEBIT ="DEBIT";
	
	public List<Transaction> listAllTransactionLogs(String loggedInUser) throws SQLException;
	public boolean isValidEncryptedString(String encrypted, String loginId);
}
