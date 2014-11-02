/**
 * 
 */
package com.asu.alliancebank.factory.impl;

import com.asu.alliancebank.domain.ITransactionTransferFund;
import com.asu.alliancebank.domain.impl.TransactionTransferFund;
import com.asu.alliancebank.factory.ITransactionTransferFundsFactory;

/**
 * @author Kedar
 *
 */
public class TransactionTransferFundsFactory implements ITransactionTransferFundsFactory {

	@Override
	public ITransactionTransferFund createTransactionTransferFund() {
		return new TransactionTransferFund();
	}

}
