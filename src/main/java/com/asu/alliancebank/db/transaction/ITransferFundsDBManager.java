package com.asu.alliancebank.db.transaction;
/**
 * @author Sravya
 *
 */
import java.sql.SQLException;
import java.util.List;

import com.asu.alliancebank.domain.ITransactionTransferFund;
import com.asu.alliancebank.domain.impl.TransferFunds;

public interface ITransferFundsDBManager {
	public String addTransferFunds(String transactionId, TransferFunds transferFunds, String loggedInUser, String otp)
			throws SQLException;
	public List<ITransactionTransferFund> getTransferFundDetails(String loggedInUser) throws SQLException;
	public List<ITransactionTransferFund> getTransferFundDetailsCust(String loggedInUser) throws SQLException;
}
