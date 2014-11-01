package com.asu.alliancebank.service.transaction;

import java.sql.SQLException;
import java.util.List;

import com.asu.alliancebank.domain.impl.Transaction;

public interface ITransactionManager {
	
	public static final int PENDING = 1;
	public static final int SUCCESS = 2;
	public static final int FAILURE = 3;
	
	public List<Transaction> listAllTransactionLogs(String loggedInUser) throws SQLException;
}