package com.asu.alliancebank.factory.impl;

import org.springframework.stereotype.Service;

import com.asu.alliancebank.domain.ITransactionCredit;
import com.asu.alliancebank.domain.impl.TransactionCredit;
import com.asu.alliancebank.factory.ITransactionCreditFactory;

@Service
public class TransactionCreditFactory implements ITransactionCreditFactory {

	
	/* (non-Javadoc)
	 * @see com.asu.alliancebank.factory.impl.ITransactionCreditFactory#createTransactionCredit()
	 */
	@Override
	public ITransactionCredit createTransactionCredit(){
		return  new TransactionCredit();
	}
}
