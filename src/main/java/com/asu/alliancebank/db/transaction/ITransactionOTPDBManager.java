package com.asu.alliancebank.db.transaction;

import java.sql.SQLException;

import com.asu.alliancebank.domain.impl.TransferFunds;

public interface ITransactionOTPDBManager {
	public boolean isTransactionOtpCorrect(String transactionId, String otp)throws SQLException;
	public String updateTransferFunds(String transactionId,TransferFunds transferFunds, String loggedInUser, String otp)
			throws SQLException;
	public TransferFunds getTransferFunds(String transactionId,String loggedInUser) throws SQLException;
}
