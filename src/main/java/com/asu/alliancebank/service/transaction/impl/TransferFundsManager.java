package com.asu.alliancebank.service.transaction.impl;
/**
 * @author Sravya
 *
 */
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.db.transaction.ITransferFundsDBManager;
import com.asu.alliancebank.domain.impl.TransferFunds;
import com.asu.alliancebank.service.transaction.ITransferFundsManager;

@Service
public class TransferFundsManager implements ITransferFundsManager{
	@Autowired
	@Qualifier("TransferFundsDBManagerBean")
	private ITransferFundsDBManager dbConnect;
		
	@Override
	public void addTransferFunds(TransferFunds transferFunds, String loggedInUser)
			throws SQLException {
		if(transferFunds != null) {
			dbConnect.addTransferFunds(transferFunds, loggedInUser);
			//dbConnect.updateAccounts(account, transferFunds, loggedInUser);
		}
	}
	
	@Override
	public List<String> listAllUserNames(String loggedInUser) throws SQLException{
		List<String> userNames = null;
		if(loggedInUser != null){
			userNames = dbConnect.getAllUserNames(loggedInUser);
		}
		return userNames;
	}
}
