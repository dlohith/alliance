/**
 * 
 */
package com.asu.alliancebank.factory;

import com.asu.alliancebank.domain.ITransactionTransferFund;

/**
 * @author Kedar
 *
 */
public interface ITransactionTransferFundsFactory {
	public abstract ITransactionTransferFund createTransactionTransferFund();
}
