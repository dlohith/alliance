package com.asu.alliancebank.service.transaction.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.db.transaction.ICreditFundsDBManager;
import com.asu.alliancebank.domain.ICreditFunds;
import com.asu.alliancebank.domain.ITransactionCredit;
import com.asu.alliancebank.domain.impl.CreditFunds;
import com.asu.alliancebank.domain.impl.User;
import com.asu.alliancebank.security.pki.impl.HashingForPKI;
import com.asu.alliancebank.service.account.IAccountManager;
import com.asu.alliancebank.service.email.IEmailManagement;
import com.asu.alliancebank.service.transaction.ICreditFundsManager;
import com.asu.alliancebank.service.user.IUserManager;
@Service
public class CreditFundsManager implements ICreditFundsManager{
	@Autowired
	@Qualifier("CreditFundsDBManagerBean")
	private ICreditFundsDBManager dbConnect;
		
	@Autowired
	private IAccountManager accountManager;
	
	@Autowired
	private IEmailManagement emailManagement;
	
	@Autowired
	private HashingForPKI hashingForPKI;
	
	@Autowired
	private IUserManager userManager;
	
	private String generateUniqueID()
	{
		return UUID.randomUUID().toString();
	}
	
	@Override
	public synchronized String addCreditFunds(CreditFunds creditFunds, String loggedInUser)
			throws SQLException {
		String transactionId = generateUniqueID();
		if(creditFunds != null) {
			
			dbConnect.addCreditFunds(creditFunds, loggedInUser,transactionId);
			String hashValue = hashingForPKI.getHashedData();
			dbConnect.addTransactionCreditHash(hashValue, loggedInUser, transactionId);
			User user = userManager.getUserDetails(loggedInUser);
			emailManagement.sendTransactionHash(transactionId, user.getEmailId(), hashValue);
		}
		return transactionId;
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

	@Override
	public List<ITransactionCredit> getCreditDetails(String loggedInUser)
			throws SQLException {
		List<ITransactionCredit> creditDetails = null;
		if(loggedInUser != null){
			creditDetails = dbConnect.getCreditDetails(loggedInUser);
		}
		return creditDetails;
	}

	@Override
	public List<ITransactionCredit> getCreditDetailsCust(String loggedInUser)
			throws SQLException {
		List<ITransactionCredit> creditDetails = null;
		if(loggedInUser != null){
			creditDetails = dbConnect.getCreditDetailsCust(loggedInUser);
		}
		return creditDetails;
	}
	
	@Override
	public boolean isTransactionHashCorrect( String transactionId, String hashValue)throws SQLException{
		return dbConnect.isTransactionHashCorrect(transactionId, hashValue);
	}
	
	@Override
	public ICreditFunds getCreditFunds(String transactionId)throws SQLException{
		return dbConnect.getCreditFundsToFinalize(transactionId);
	}
	
	@Override
	public void finalizeTransactionCredit( String transactionId,ICreditFunds creditFunds, String loggedInUser)throws SQLException{
		dbConnect.finalizeTransactionCredit(transactionId, creditFunds, loggedInUser);
	}
	
}
