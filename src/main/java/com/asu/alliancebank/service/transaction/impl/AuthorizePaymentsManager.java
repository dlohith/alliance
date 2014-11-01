/**
 * 
 */
package com.asu.alliancebank.service.transaction.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.db.transaction.IAuthorizePaymentsDBManager;
import com.asu.alliancebank.domain.impl.MerchantRequest;
import com.asu.alliancebank.service.transaction.IAuthorizePaymentsManager;

/**
 * @author Kedar
 *
 */
@Service
public class AuthorizePaymentsManager implements IAuthorizePaymentsManager  {

	@Autowired
	@Qualifier("AuthorizePaymentManager")
	private IAuthorizePaymentsDBManager dbConnect;
	
	@Override
	public List<MerchantRequest> getPendingRequests(String loggedInUser)
			throws SQLException {
		List<MerchantRequest> pendingRequests = dbConnect.getPendingMerchantRequests(loggedInUser);
		return pendingRequests;
	}

	@Override
	public MerchantRequest getMerchantRequest(String requestID)
			throws SQLException {
		MerchantRequest merchantRequest = null;
		if(requestID != null) {
			merchantRequest = dbConnect.getMerchantRequest(requestID);
		}
		return merchantRequest;
	}

	@Override
	public String approvePayment(MerchantRequest merchantRequest,
			String loggedInUser) throws SQLException {
		return dbConnect.approvePayment(merchantRequest, loggedInUser);
	}

	@Override
	public String rejectPayment(MerchantRequest merchantRequest,
			String loggedInUser) throws SQLException {
		return dbConnect.rejectPayment(merchantRequest, loggedInUser);
	}

}
