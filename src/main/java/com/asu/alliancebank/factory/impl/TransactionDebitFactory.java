package com.asu.alliancebank.factory.impl;

import org.springframework.stereotype.Service;

import com.asu.alliancebank.domain.ITransactionDebit;
import com.asu.alliancebank.domain.impl.TransactionDebit;
import com.asu.alliancebank.factory.ITransactionDebitFactory;

@Service
public class TransactionDebitFactory implements ITransactionDebitFactory {

	
	/* (non-Javadoc)
	 * @see com.asu.alliancebank.factory.impl.ITransactionCreditFactory#createTransactionCredit()
	 */
	@Override
	public ITransactionDebit createTransactionDebit(){
		return  new TransactionDebit();
	}
}
