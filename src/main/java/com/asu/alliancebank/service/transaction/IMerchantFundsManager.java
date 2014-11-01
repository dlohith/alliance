/**
 * 
 */
package com.asu.alliancebank.service.transaction;

import java.sql.SQLException;

import com.asu.alliancebank.controllers.transactionmanagement.backingbean.MerchantDebitFundsBackingBean;

/**
 * @author Kedar
 *
 */
public interface IMerchantFundsManager {
	public abstract void addMerchantRequest(MerchantDebitFundsBackingBean merchantBackingBean, String loggedInUser) throws SQLException;
}
