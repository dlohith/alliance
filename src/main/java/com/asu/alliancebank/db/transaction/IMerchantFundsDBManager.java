/**
 * 
 */
package com.asu.alliancebank.db.transaction;

import java.sql.SQLException;
import java.util.List;

import com.asu.alliancebank.controllers.transactionmanagement.backingbean.MerchantDebitFundsBackingBean;
import com.asu.alliancebank.domain.impl.MerchantRequest;

/**
 * @author Kedar
 *
 */
public interface IMerchantFundsDBManager {
	public String addMerchantRequest(MerchantDebitFundsBackingBean merchantBackingBean, String loggedInUser) throws SQLException;
	public List<MerchantRequest> getAllMerchantRequests(String loggedInUser) throws SQLException;
	public List<MerchantRequest> getAllMerchantRequestsMerchant(String loggedInUser) throws SQLException;
	public String getUserIdForMerchantRequests(String requestID)
			throws SQLException;
	public Double getAmountForMerchantRequests(String requestID)
			throws SQLException;
}
