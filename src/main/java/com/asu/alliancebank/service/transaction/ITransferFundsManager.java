package com.asu.alliancebank.service.transaction;
/**
 * @author Sravya
 *
 */
import java.sql.SQLException;
import java.util.List;

import com.asu.alliancebank.domain.impl.TransferFunds;

public interface ITransferFundsManager {
	public abstract void addTransferFunds(TransferFunds transferFunds, String loggedInUser) throws SQLException;
	public abstract List<String> listAllUserNames(String loggedInUser) throws SQLException;
}
