package com.asu.alliancebank.factory.impl;


import org.springframework.stereotype.Service;

import com.asu.alliancebank.controllers.transactionmanagement.backingbean.CreditFundsBackingBean;
import com.asu.alliancebank.domain.impl.CreditFunds;
import com.asu.alliancebank.factory.ICreditFundsFactory;
@Service
public class CreditFundsFactory implements ICreditFundsFactory{
	
	@Override
	public CreditFunds createEmptyCreditFundsObject(){
		return new CreditFunds();
	}

	@Override
	public CreditFunds createCreditFundsInstance(CreditFundsBackingBean creditFundsBackingBean) {
		return new CreditFunds(creditFundsBackingBean.getAccountID(), creditFundsBackingBean.getAmount()); 				
	}

	@Override
	public CreditFunds createCreditFundsInstance(String accountId,String amount) {
		return new CreditFunds(accountId,amount);
	}
}
