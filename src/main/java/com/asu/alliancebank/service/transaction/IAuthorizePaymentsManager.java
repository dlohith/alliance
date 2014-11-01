/**
 * 
 */
package com.asu.alliancebank.service.transaction;

import java.sql.SQLException;
import java.util.List;

import com.asu.alliancebank.domain.impl.MerchantRequest;

/**
 * @author Kedar
 *
 */
public interface IAuthorizePaymentsManager {
	public List<MerchantRequest> getPendingRequests(String loggedInUser) throws SQLException;
	public MerchantRequest getMerchantRequest(String requestID) throws SQLException;
	public String approvePayment(MerchantRequest merchantRequest, String loggedInUser) throws SQLException;
	public String rejectPayment(MerchantRequest merchantRequest, String loggedInUser) throws SQLException;
}
