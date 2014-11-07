package com.asu.alliancebank.service.transaction.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.db.transaction.ITransactionDBManager;
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
	public boolean isValidEncryptedString(String encrypted, String loginId){
		
		return pkiManager.isResponseValid(encrypted, loginId);
		
	}
}
