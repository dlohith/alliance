/**
 * 
 */
package com.asu.alliancebank.factory.impl;

import org.springframework.stereotype.Service;

import com.asu.alliancebank.controllers.accountmanagement.backingbean.AccountBackingBean;
import com.asu.alliancebank.domain.impl.Account;
import com.asu.alliancebank.factory.IAccountFactory;

/**
 * @author Kedar
 *
 */
@Service
public class AccountFactory implements IAccountFactory {
	
	@Override
	public Account createEmptyAccountObject(){
		return new Account();
	}

	@Override
	public Account createAccountInstance(String userID, Long balance) {
		return new Account(userID, balance);
	}

	@Override
	public Account createAccountInstance(AccountBackingBean accountBackingBean) {
		return new Account(accountBackingBean.getUserID(),
				accountBackingBean.getBalance());
	}
}
