package com.asu.alliancebank.service.transaction.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.db.transaction.ITransactionDBManager;
import com.asu.alliancebank.domain.impl.Transaction;
import com.asu.alliancebank.security.pki.impl.PKIManager;
import com.asu.alliancebank.service.transaction.ITransactionManager;

@Service
public class TransactionManager implements ITransactionManager{
	
	@Autowired
	@Qualifier("TransactionDBManagerBean")
	private ITransactionDBManager dbConnect;
	
	@Autowired
	private PKIManager pkiManager;
		
	@Override
	public List<Transaction> listAllTransactionLogs(String loggedInUser) throws SQLException{
		List<Transaction> transactionList = null;
		if(loggedInUser != null){
			transactionList = dbConnect.listAllTransactionLogs(loggedInUser);
		}
		return transactionList;
	}
	
	@Override
	public boolean isValidEncryptedString(String encrypted, String loginId){
		
		return pkiManager.isResponseValid(encrypted, loginId);
		
	}
}
