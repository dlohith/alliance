package com.asu.alliancebank.service.transaction;

import java.sql.SQLException;

import com.asu.alliancebank.domain.impl.DebitFunds;

public interface IDebitFundsManager {
	
	public static final String DEBIT = "Debit";
	public abstract void addDebitFunds(DebitFunds debitFunds,String loggedInUser) throws SQLException;
}
