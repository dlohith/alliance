/**
 * 
 */
package com.asu.alliancebank.service.transaction.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.controllers.transactionmanagement.backingbean.MerchantDebitFundsBackingBean;
import com.asu.alliancebank.db.transaction.IMerchantFundsDBManager;
import com.asu.alliancebank.service.transaction.IMerchantFundsManager;


/**
 * @author Kedar
 *
 */
@Service
public class MerchantFundsManager implements IMerchantFundsManager {

	@Autowired
	@Qualifier("MerchantFundsDBManagerBean")
	private IMerchantFundsDBManager dbConnect;
	
	@Override
	public void addMerchantRequest(
			MerchantDebitFundsBackingBean merchantBackingBean,
			String loggedInUser) throws SQLException {
		if(loggedInUser != null) {
			dbConnect.addMerchantRequest(merchantBackingBean, loggedInUser);
		}
	}

}
