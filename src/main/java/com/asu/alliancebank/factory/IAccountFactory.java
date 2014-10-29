/**
 * 
 */
package com.asu.alliancebank.factory;

import com.asu.alliancebank.controllers.accountmanagement.backingbean.AccountBackingBean;
import com.asu.alliancebank.domain.impl.Account;

/**
 * @author Kedar
 *
 */
public interface IAccountFactory {
	
	public Account createEmptyAccountObject();
	public Account createAccountInstance(AccountBackingBean accountBackingBean);
	public Account createAccountInstance(String userID, Long balance);

}
