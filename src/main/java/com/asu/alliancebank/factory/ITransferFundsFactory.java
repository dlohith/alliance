package com.asu.alliancebank.factory;

/**
 * @author Sravya
 *
 */

import com.asu.alliancebank.controllers.transactionmanagement.backingbean.TransferFundsBackingBean;
import com.asu.alliancebank.domain.impl.TransferFunds;

public interface ITransferFundsFactory {
	public TransferFunds createEmptyTransferFundsObject();
	public TransferFunds createTransferFundsInstance(TransferFundsBackingBean transferFundsBackingBean);
	public TransferFunds createTransferFundsInstance(String fromAccountId, String toAccountId ,Long amount);
}
