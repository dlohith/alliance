package com.asu.alliancebank.db.transaction;

import java.sql.SQLException;

public interface ITransactionOTPDBManager {
	public boolean isTransactionOtpCorrect(String transactionId, String otp)throws SQLException;
}
