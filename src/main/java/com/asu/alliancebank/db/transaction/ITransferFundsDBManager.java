package com.asu.alliancebank.db.transaction;
/**
 * @author Sravya
 *
 */
import java.sql.SQLException;

import com.asu.alliancebank.domain.impl.TransferFunds;

public interface ITransferFundsDBManager {
	public String addTransferFunds(String transactionId, TransferFunds transferFunds, String loggedInUser, String otp)
			throws SQLException;

	
}
