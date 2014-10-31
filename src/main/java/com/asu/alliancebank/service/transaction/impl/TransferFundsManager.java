package com.asu.alliancebank.service.transaction.impl;
/**
 * @author Sravya
 *
 */
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.db.transaction.ITransferFundsDBManager;
import com.asu.alliancebank.domain.impl.TransferFunds;
import com.asu.alliancebank.domain.impl.User;
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
		List<User> users = userManager.listAllUser(loggedInUser);
		List<String> userNames = new ArrayList<String>();
		
		for(User user : users){
			for(AllianceBankGrantedAuthority authority : user.getAuthorities()){
				if(authority.getAuthority().equals(IRoleManager.ROLE_INDIVIDUAL_CUSTOMER)){
					if(user.getLoginID() != loggedInUser){
						userNames.add(user.getLoginID());
					}
				}
			}
		}

		return userNames;
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
}
