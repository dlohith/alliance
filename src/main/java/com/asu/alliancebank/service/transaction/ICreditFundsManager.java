package com.asu.alliancebank.service.transaction;

import java.sql.SQLException;
import java.util.List;

import com.asu.alliancebank.domain.ITransactionCredit;
import com.asu.alliancebank.domain.impl.CreditFunds;

public interface ICreditFundsManager {
	public static final String CREDIT = "C";
	public abstract void addCreditFunds(CreditFunds creditFunds, String loggedInUser) throws SQLException;
	public abstract boolean isValid(String loggedInUser, String amount) throws SQLException;
	public abstract List<ITransactionCredit> getCreditDetails(String loggedInUser) throws SQLException;
	public abstract List<ITransactionCredit> getCreditDetailsCust(String loggedInUser) throws SQLException;
}
