package com.asu.alliancebank.db.transaction;

import java.sql.SQLException;
import java.util.List;

import com.asu.alliancebank.domain.ITransactionCredit;
import com.asu.alliancebank.domain.impl.CreditFunds;

public interface ICreditFundsDBManager {
	public String addCreditFunds(CreditFunds creditFunds, String loggedInUser) throws SQLException;	
	public List<ITransactionCredit> getCreditDetails(String loggedInUser) throws SQLException;
	public List<ITransactionCredit> getCreditDetailsCust(String loggedInUser) throws SQLException;
	}
