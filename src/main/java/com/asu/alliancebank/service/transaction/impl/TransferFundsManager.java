package com.asu.alliancebank.service.transaction.impl;
/**
 * @author Sravya
 *
 */
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.db.transaction.ITransferFundsDBManager;
import com.asu.alliancebank.domain.ITransactionTransferFund;
import com.asu.alliancebank.domain.impl.TransferFunds;
import com.asu.alliancebank.domain.impl.User;
import com.asu.alliancebank.security.otp.impl.OTPManager;
import com.asu.alliancebank.service.account.IAccountManager;
import com.asu.alliancebank.service.role.IRoleManager;
import com.asu.alliancebank.service.transaction.ITransferFundsManager;
import com.asu.alliancebank.service.user.IUserManager;
import com.asu.alliancebank.service.userservice.AllianceBankGrantedAuthority;

@Service
public class TransferFundsManager implements ITransferFundsManager{
	@Autowired
	@Qualifier("TransferFundsDBManagerBean")
	private ITransferFundsDBManager dbConnect;
	
	@Autowired
	private IUserManager userManager;
	
	@Autowired
	private IAccountManager accountManager;
	
	@Autowired
	private OTPManager otpManager;
	
	private String generateUniqueID()
	{
		return UUID.randomUUID().toString();
	}
	
	@Override
	public String  addTransferFunds(TransferFunds transferFunds, String loggedInUser)
			throws SQLException {
		String transactionId = generateUniqueID();
		if(transferFunds != null) {
			int otp = otpManager.getOTP();
			dbConnect.addTransferFunds(transactionId,transferFunds, loggedInUser, otp+"");
		}
		
		return transactionId;
	}
	
	@Override
	public List<String> listAllUserNames(String loggedInUser) throws SQLException{
		List<String> users = new ArrayList<String>();
		users = accountManager.listAllUserLoginIdsFromAccounts(loggedInUser);
		List<String> userList = new ArrayList<String>();
		for(String user : users){
			if(!user.equals(loggedInUser)){
				userList.add(user);
			}
		}		
		return userList;
	}
	
	@Override
	public boolean isValid(String loggedInUser, String amount)
			throws SQLException {
		Long balance = null;
		try {
			balance = accountManager.getAccountBalance(loggedInUser);
		} catch (SQLException e1) {
			return false;
		}

		try {
			Long amountLong = Long.parseLong(amount);
			if (balance - amountLong > 0) {
				return true;
			}
		} catch (Exception e) {	
		}
		return false;

	}

	@Override
	public List<ITransactionTransferFund> getTransferDetails(String loggedInUser)
			throws SQLException {
		List<ITransactionTransferFund> transferDetails = null;
		if(loggedInUser != null) {
			transferDetails = dbConnect.getTransferFundDetails(loggedInUser);
		}
		return transferDetails;
	}

	@Override
	public List<ITransactionTransferFund> getTransferDetailsCust(
			String loggedInUser) throws SQLException {
		List<ITransactionTransferFund> transferDetails = null;
		if(loggedInUser != null) {
			transferDetails = dbConnect.getTransferFundDetailsCust(loggedInUser);
		}
		return transferDetails;
	}
}
