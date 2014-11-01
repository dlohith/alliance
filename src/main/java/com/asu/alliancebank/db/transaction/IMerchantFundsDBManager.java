/**
 * 
 */
package com.asu.alliancebank.db.transaction;

import java.sql.SQLException;

import com.asu.alliancebank.controllers.transactionmanagement.backingbean.MerchantDebitFundsBackingBean;

/**
 * @author Kedar
 *
 */
public interface IMerchantFundsDBManager {
	public String addMerchantRequest(MerchantDebitFundsBackingBean merchantBackingBean, String loggedInUser) throws SQLException;
}
