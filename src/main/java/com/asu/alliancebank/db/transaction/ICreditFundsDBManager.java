package com.asu.alliancebank.db.transaction;

import java.sql.SQLException;

import com.asu.alliancebank.domain.impl.CreditFunds;

public interface ICreditFundsDBManager {
	public String addCreditFunds(CreditFunds creditFunds, String loggedInUser) throws SQLException;	
	}
