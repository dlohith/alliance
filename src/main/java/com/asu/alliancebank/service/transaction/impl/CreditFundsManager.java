package com.asu.alliancebank.service.transaction.impl;

import java.sql.SQLException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.db.transaction.ICreditFundsDBManager;
import com.asu.alliancebank.domain.impl.CreditFunds;
import com.asu.alliancebank.service.account.IAccountManager;
import com.asu.alliancebank.service.transaction.ICreditFundsManager;
@Service
public class CreditFundsManager implements ICreditFundsManager{
	@Autowired
	@Qualifier("CreditFundsDBManagerBean")
	private ICreditFundsDBManager dbConnect;
		
	@Autowired
	private IAccountManager accountManager;
	
	@Override
	public void addCreditFunds(CreditFunds creditFunds, String loggedInUser)
			throws SQLException {
		if(creditFunds != null) {
			dbConnect.addCreditFunds(creditFunds, loggedInUser);
		}
	}

	@Override
	public boolean isValid(String loggedInUser, String amount)
			throws SQLException {
		Long balance = null;
		try {
			balance = accountManager.getAccountBalance(loggedInUser);
		} catch (SQLException e1) {
			return false;
		}

		try {
			Long amountLong = Long.parseLong(amount);
			if (balance + amountLong < 100000) {
				return true;
			}
		} catch (Exception e) {	
		}
		return false;
	}
	
	
}
