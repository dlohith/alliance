package com.asu.alliancebank.db.transaction;

import java.sql.SQLException;
import java.util.List;

import com.asu.alliancebank.domain.IDebitFunds;
import com.asu.alliancebank.domain.ITransactionDebit;
import com.asu.alliancebank.domain.impl.DebitFunds;

public interface IDebitFundsDBManager {
	public String addDebitFunds(DebitFunds debitFunds, String loggedInUser,String transactionId)
			throws SQLException;

	public List<ITransactionDebit> getDebitDetails(String loggedInUser)
			throws SQLException;

	public List<ITransactionDebit> getDebitDetailsCust(String loggedInUser)
			throws SQLException;

	public String addTransactionDebitHash(String hashValue,
			String loggedInUser, String transactionId) throws SQLException;

	public boolean isTransactionHashCorrect(String transactionId,
			String hashValue) throws SQLException;

	public IDebitFunds getDebitFundsToFinalize(String transactionId)
			throws SQLException;

	public String finalizeTransactionDebit(String transactionId,
			IDebitFunds debitFunds, String loggedInUser) throws SQLException;
}
