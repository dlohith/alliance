/**
 * 
 */
package com.asu.alliancebank.service.transaction;

import java.sql.SQLException;
import java.util.List;

import com.asu.alliancebank.controllers.transactionmanagement.backingbean.MerchantDebitFundsBackingBean;
import com.asu.alliancebank.domain.impl.MerchantRequest;

/**
 * @author Kedar
 *
 */
public interface IMerchantFundsManager {
	public abstract void addMerchantRequest(MerchantDebitFundsBackingBean merchantBackingBean, String loggedInUser) throws SQLException;
	public abstract List<MerchantRequest> getMerchantRequests(String loggedInUser) throws SQLException;
	public abstract List<MerchantRequest> getMerchantRequestsMerchant(String loggedInUser) throws SQLException;
	public String getUserIdForMerchantRequests(String requestID)
			throws SQLException;
	public Double getAmountForMerchantRequests(String requestID)
			throws SQLException;
}
