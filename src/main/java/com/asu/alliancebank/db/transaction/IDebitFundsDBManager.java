package com.asu.alliancebank.db.transaction;

import java.sql.SQLException;
import java.util.List;

import com.asu.alliancebank.domain.ITransactionDebit;
import com.asu.alliancebank.domain.impl.DebitFunds;

public interface IDebitFundsDBManager {
	public String addDebitFunds(DebitFunds debitFunds, String loggedInUser) throws SQLException;
	public List<ITransactionDebit> getDebitDetails(String loggedInUser) throws SQLException;
	public List<ITransactionDebit> getDebitDetailsCust(String loggedInUser) throws SQLException;
	}
