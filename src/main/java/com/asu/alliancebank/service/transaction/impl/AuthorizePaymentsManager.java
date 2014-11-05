/**
 * 
 */
package com.asu.alliancebank.service.transaction.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.db.transaction.IAuthorizePaymentsDBManager;
import com.asu.alliancebank.domain.impl.MerchantRequest;
import com.asu.alliancebank.domain.impl.User;
import com.asu.alliancebank.service.role.IRoleManager;
import com.asu.alliancebank.service.transaction.IAuthorizePaymentsManager;
import com.asu.alliancebank.service.user.IUserManager;
import com.asu.alliancebank.service.userservice.AllianceBankGrantedAuthority;

/**
 * @author Kedar
 *
 */
@Service
public class AuthorizePaymentsManager implements IAuthorizePaymentsManager  {

	@Autowired
	@Qualifier("AuthorizePaymentManager")
	private IAuthorizePaymentsDBManager dbConnect;
	
	@Autowired
	private IUserManager userManager;
	
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

	@Override
	public boolean areContentsValid(String merchantID, String userLoginID, String loggedInUser) throws SQLException {
		List<MerchantRequest> pendingRequests = dbConnect.getPendingMerchantRequests(loggedInUser);
		for(MerchantRequest merchantRequest : pendingRequests) {
			if(merchantRequest.getMerchantID().equals(merchantID) && merchantRequest.getUserLoginID().equals(userLoginID))
				return true;
		}
		return false;
	}

	@Override
	public void addCustAuthRequest(String loggedInUser) throws SQLException {
		if(loggedInUser != null) {
			dbConnect.addCustomerAuthRequest(loggedInUser);
		}
	}

	@Override
	public List<String> getUsersForCustAuth(String loggedInUser)
			throws SQLException {
		List<String> userList = null;
		if(loggedInUser != null) {
			userList = dbConnect.getCustomerListForDisplay(loggedInUser);
		}
		return userList;
	}

	@Override
	public List<String> getBankEmployeeList(String loggedInUser)
			throws SQLException {
		List<String> employeeList = new ArrayList<String>();
		if(loggedInUser != null) {
			List<User> users = userManager.listAllUser(loggedInUser);
			for(User user : users){
				for(AllianceBankGrantedAuthority authority : user.getAuthorities()){
					if(authority.getAuthority().equals(IRoleManager.ROLE_BANK_EMPLOYEE)){
						if(user.getLoginID() != loggedInUser){
							employeeList.add(user.getLoginID());
						}
					}
				}
			}
		}
		return employeeList;
	}

	@Override
	public void authorizeCustAuthRequest(String userLoginID, String employeeLoginID, String loggedInUser)
			throws SQLException {
		if(loggedInUser != null) {
			dbConnect.authorizeCustAuthRequest(userLoginID, employeeLoginID, loggedInUser);
		}
	}

	@Override
	public List<MerchantRequest> getPendingMerchantRequestsEmployee(
			String loggedInUser) throws SQLException {
		List<MerchantRequest> employeeMerchReqLsit = new ArrayList<MerchantRequest>();
		if(loggedInUser != null) {
			employeeMerchReqLsit = dbConnect.getPendingMerchantRequestsEmployee(loggedInUser);
		}
		return employeeMerchReqLsit;
	}

}
