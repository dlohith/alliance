package com.asu.alliancebank.service.transaction;

import java.sql.SQLException;
import java.util.List;

import com.asu.alliancebank.domain.IDebitFunds;
import com.asu.alliancebank.domain.ITransactionDebit;
import com.asu.alliancebank.domain.impl.DebitFunds;

public interface IDebitFundsManager {
	
	public static final String DEBIT = "Debit";
	public abstract String addDebitFunds(DebitFunds debitFunds,String loggedInUser) throws SQLException;
	public abstract List<ITransactionDebit> getDebitDetails(String loggedInUser) throws SQLException;
	public abstract List<ITransactionDebit> getDebitDetailsCust(String loggedInUser) throws SQLException;
	public boolean isTransactionHashCorrect( String transactionId, String hashValue)throws SQLException;
	public IDebitFunds getDebitFunds(String transactionId)throws SQLException;
	public void finalizeTransactionDebit( String transactionId,IDebitFunds debitFunds, String loggedInUser)throws SQLException;
}
