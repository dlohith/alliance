/**
 * 
 */
package com.asu.alliancebank.db.transaction;

import java.sql.SQLException;
import java.util.List;

import com.asu.alliancebank.domain.impl.MerchantRequest;

/**
 * @author Kedar
 *
 */
public interface IAuthorizePaymentsDBManager {
	// for merchants
	public String approvePayment(MerchantRequest merchantRequest, String loggedInUser) throws SQLException;
	public String rejectPayment(MerchantRequest merchantRequest, String loggedInUser) throws SQLException;
	public MerchantRequest getMerchantRequest(String requestID) throws SQLException;
	public List<MerchantRequest> getPendingMerchantRequests(String loggedInUser) throws SQLException;
	
	// for internal bank employee functionality
	public String addCustomerAuthRequest(String loggedInUser) throws SQLException;
	public List<String> getCustomerListForDisplay(String loggedInUser) throws SQLException;
	public String authorizeCustAuthRequest(String userLoginID, String employeeLoginID, String loggedInUser) throws SQLException;
	public List<MerchantRequest> getPendingMerchantRequestsEmployee(String loggedInUser) throws SQLException;
}
