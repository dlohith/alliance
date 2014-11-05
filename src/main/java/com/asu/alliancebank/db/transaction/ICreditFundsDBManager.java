package com.asu.alliancebank.db.transaction;

import java.sql.SQLException;
import java.util.List;

import com.asu.alliancebank.domain.ICreditFunds;
import com.asu.alliancebank.domain.ITransactionCredit;
import com.asu.alliancebank.domain.impl.CreditFunds;

public interface ICreditFundsDBManager {
	public String addCreditFunds(CreditFunds creditFunds, String loggedInUser,
			String transactionId) throws SQLException;

	public String addTransactionCreditHash(String hashValue,
			String loggedInUser, String transactionId) throws SQLException;

	public List<ITransactionCredit> getCreditDetails(String loggedInUser)
			throws SQLException;

	public List<ITransactionCredit> getCreditDetailsCust(String loggedInUser)
			throws SQLException;

	public boolean isTransactionHashCorrect(String transactionId,
			String hashValue) throws SQLException;

	public ICreditFunds getCreditFundsToFinalize(String transactionId)
			throws SQLException;
	public String finalizeTransactionCredit( String transactionId,ICreditFunds creditFunds, String loggedInUser)throws SQLException;

}
