package com.asu.alliancebank.factory.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.controllers.transactionmanagement.backingbean.DebitFundsBackingBean;
import com.asu.alliancebank.domain.impl.DebitFunds;
import com.asu.alliancebank.factory.IDebitFundsFactory;
@Service
public class DebitFundsFactory implements IDebitFundsFactory{
	private static final Logger logger = LoggerFactory
			.getLogger(DebitFundsFactory.class);
	
	@Override
	public DebitFunds createEmptyDebitFundsObject(){
		return new DebitFunds();
	}

	@Override
	public DebitFunds createDebitFundsInstance(DebitFundsBackingBean debitFundsBackingBean) {
		return new DebitFunds(debitFundsBackingBean.getAmount()); 				
	}

	@Override
	public DebitFunds createDebitFundsInstance(String amount) {
		return new DebitFunds(amount);
	}
}
