package com.asu.alliancebank.db.transaction;

import java.sql.SQLException;

import com.asu.alliancebank.domain.impl.DebitFunds;

public interface IDebitFundsDBManager {
	public String addDebitFunds(DebitFunds debitFunds, String loggedInUser) throws SQLException;	
	}
