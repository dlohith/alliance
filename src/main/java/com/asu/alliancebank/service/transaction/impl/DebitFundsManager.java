package com.asu.alliancebank.service.transaction.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.db.transaction.IDebitFundsDBManager;
import com.asu.alliancebank.domain.IDebitFunds;
import com.asu.alliancebank.domain.ITransactionDebit;
import com.asu.alliancebank.domain.impl.DebitFunds;
import com.asu.alliancebank.domain.impl.User;
import com.asu.alliancebank.security.pki.impl.HashingForPKI;
import com.asu.alliancebank.service.email.IEmailManagement;
import com.asu.alliancebank.service.transaction.IDebitFundsManager;
import com.asu.alliancebank.service.user.IUserManager;

@Service
public class DebitFundsManager implements IDebitFundsManager{
	@Autowired
	@Qualifier("DebitFundsDBManagerBean")
	private IDebitFundsDBManager dbConnect;
	
	@Autowired
	private HashingForPKI hashingForPKI;
	
	@Autowired
	private IUserManager userManager;
	
	@Autowired
	private IEmailManagement emailManagement;
	
	private String generateUniqueID()
	{
		return UUID.randomUUID().toString();
	}
		
	@Override
	public synchronized String addDebitFunds(DebitFunds debitFunds, String loggedInUser)
			throws SQLException {
		
		String transactionId = generateUniqueID();
		if(debitFunds != null) {
			
			dbConnect.addDebitFunds(debitFunds, loggedInUser,transactionId);
			String hashValue = hashingForPKI.getHashedData();
			dbConnect.addTransactionDebitHash(hashValue, loggedInUser, transactionId);
			User user = userManager.getUserDetails(loggedInUser);
			emailManagement.sendTransactionHash(transactionId, user.getEmailId(), hashValue);
		}
		return transactionId;
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
	
	@Override
	public boolean isTransactionHashCorrect( String transactionId, String hashValue)throws SQLException{
		return dbConnect.isTransactionHashCorrect(transactionId, hashValue);
	}
	
	@Override
	public IDebitFunds getDebitFunds(String transactionId)throws SQLException{
		return dbConnect.getDebitFundsToFinalize(transactionId);
	}
	
	@Override
	public void finalizeTransactionDebit( String transactionId,IDebitFunds debitFunds, String loggedInUser)throws SQLException{
		dbConnect.finalizeTransactionDebit(transactionId, debitFunds, loggedInUser);
	}
}
