package com.asu.alliancebank.service.transaction.impl;

import java.sql.SQLException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.db.transaction.IDebitFundsDBManager;
import com.asu.alliancebank.domain.impl.DebitFunds;
import com.asu.alliancebank.service.transaction.IDebitFundsManager;

@Service
public class DebitFundsManager implements IDebitFundsManager{
	@Autowired
	@Qualifier("DebitFundsDBManagerBean")
	private IDebitFundsDBManager dbConnect;
		
	@Override
	public void addDebitFunds(DebitFunds debitFunds, String loggedInUser)
			throws SQLException {
		if(debitFunds != null) {
			dbConnect.addDebitFunds(debitFunds, loggedInUser);
			//dbConnect.updateAccounts(account, debitFunds, loggedInUser);
		}
	}
		
}
