package com.asu.alliancebank.db.transaction;

import java.sql.SQLException;
import java.util.List;

import com.asu.alliancebank.domain.impl.Transaction;

public interface ITransactionDBManager {
	public List<Transaction> listAllTransactionLogs(String loggedInUser)
			throws SQLException;
}
