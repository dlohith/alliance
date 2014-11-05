package com.asu.alliancebank.service.transaction;

import java.sql.SQLException;
import java.util.List;

import com.asu.alliancebank.domain.ICreditFunds;
import com.asu.alliancebank.domain.ITransactionCredit;
import com.asu.alliancebank.domain.impl.CreditFunds;

public interface ICreditFundsManager {
	public static final String CREDIT = "C";
	public abstract String addCreditFunds(CreditFunds creditFunds, String loggedInUser) throws SQLException;
	public abstract boolean isValid(String loggedInUser, String amount) throws SQLException;
	public abstract List<ITransactionCredit> getCreditDetails(String loggedInUser) throws SQLException;
	public abstract List<ITransactionCredit> getCreditDetailsCust(String loggedInUser) throws SQLException;
	public boolean isTransactionHashCorrect( String transactionId, String hashValue)throws SQLException;
	public ICreditFunds getCreditFunds(String transactionId)throws SQLException;
	public void finalizeTransactionCredit( String transactionId,ICreditFunds creditFunds, String loggedInUser)throws SQLException;
}
