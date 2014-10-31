package com.asu.alliancebank.db.transaction;
/**
 * @author Sravya
 *
 */
import java.sql.SQLException;
import java.util.List;

import com.asu.alliancebank.domain.impl.TransferFunds;

public interface ITransferFundsDBManager {
	public String addTransferFunds(TransferFunds transferFunds, String loggedInUser)
			throws SQLException;
	public List<String> getAllUserNames(String loggedInUser)
			throws SQLException;
}
