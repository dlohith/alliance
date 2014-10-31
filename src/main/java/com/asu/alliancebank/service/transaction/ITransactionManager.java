package com.asu.alliancebank.service.transaction;

import java.sql.SQLException;
import java.util.List;

import com.asu.alliancebank.domain.impl.Transaction;

public interface ITransactionManager {
	public List<Transaction> listAllTransactionLogs(String loggedInUser) throws SQLException;
}
