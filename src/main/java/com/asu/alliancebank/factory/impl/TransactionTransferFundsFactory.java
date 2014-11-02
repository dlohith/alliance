/**
 * 
 */
package com.asu.alliancebank.factory.impl;

import org.springframework.stereotype.Service;

import com.asu.alliancebank.domain.ITransactionTransferFund;
import com.asu.alliancebank.domain.impl.TransactionTransferFund;
import com.asu.alliancebank.factory.ITransactionTransferFundsFactory;

/**
 * @author Kedar
 *
 */
@Service
public class TransactionTransferFundsFactory implements ITransactionTransferFundsFactory {

	@Override
	public ITransactionTransferFund createTransactionTransferFund() {
		return new TransactionTransferFund();
	}

}
