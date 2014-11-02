package com.asu.alliancebank.service.transaction.impl;

import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.db.transaction.IDebitFundsDBManager;
import com.asu.alliancebank.domain.ITransactionDebit;
import com.asu.alliancebank.domain.impl.DebitFunds;
import com.asu.alliancebank.service.transaction.IDebitFundsManager;

@Service
public class DebitFundsManager implements IDebitFundsManager{
	@Autowired
	@Qualifier("DebitFundsDBManagerBean")
	private IDebitFundsDBManager dbConnect;
		
	@Override
	public synchronized void addDebitFunds(DebitFunds debitFunds, String loggedInUser)
			throws SQLException {
		if(debitFunds != null) {
			dbConnect.addDebitFunds(debitFunds, loggedInUser);
			//dbConnect.updateAccounts(account, debitFunds, loggedInUser);
		}
	}

	@Override
	public List<ITransactionDebit> getDebitDetails(String loggedInUser)
			throws SQLException {
		List<ITransactionDebit> debitDetails = null;
		if(loggedInUser != null) {
			debitDetails = dbConnect.getDebitDetails(loggedInUser);
		}
		return debitDetails;
	}

	@Override
	public List<ITransactionDebit> getDebitDetailsCust(String loggedInUser)
			throws SQLException {
		List<ITransactionDebit> debitDetails = null;
		if(loggedInUser != null) {
			debitDetails = dbConnect.getDebitDetailsCust(loggedInUser);
		}
		return debitDetails;
	}	
}
