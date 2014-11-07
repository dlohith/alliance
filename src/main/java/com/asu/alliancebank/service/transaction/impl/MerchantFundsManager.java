/**
 * 
 */
package com.asu.alliancebank.service.transaction.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.controllers.transactionmanagement.backingbean.MerchantDebitFundsBackingBean;
import com.asu.alliancebank.db.transaction.IMerchantFundsDBManager;
import com.asu.alliancebank.domain.impl.MerchantRequest;
import com.asu.alliancebank.service.transaction.IMerchantFundsManager;


/**
 * @author Kedar
 *
 */
@Service
public class MerchantFundsManager implements IMerchantFundsManager {

	@Autowired
	@Qualifier("MerchantFundsDBManagerBean")
	private IMerchantFundsDBManager dbConnect;
	
	@Override
	public void addMerchantRequest(
			MerchantDebitFundsBackingBean merchantBackingBean,
			String loggedInUser) throws SQLException {
		if(loggedInUser != null) {
			dbConnect.addMerchantRequest(merchantBackingBean, loggedInUser);
		}
	}

	@Override
	public List<MerchantRequest> getMerchantRequests(String loggedInUser)
			throws SQLException {
		List<MerchantRequest> merchantDetails = null;
		if(loggedInUser != null) {
			merchantDetails = dbConnect.getAllMerchantRequests(loggedInUser);
		}
		return merchantDetails;
	}

	@Override
	public List<MerchantRequest> getMerchantRequestsMerchant(String loggedInUser)
			throws SQLException {
		List<MerchantRequest> merchantDetails = null;
		if(loggedInUser != null) {
			merchantDetails = dbConnect.getAllMerchantRequestsMerchant(loggedInUser);
		}
		return merchantDetails;
	}

	@Override
	public String getUserIdForMerchantRequests(String requestID)
			throws SQLException{
		
		return dbConnect.getUserIdForMerchantRequests(requestID);
	}
	
	@Override
	public Double getAmountForMerchantRequests(String requestID)
			throws SQLException{
		
		return dbConnect.getAmountForMerchantRequests(requestID);
	}
}
