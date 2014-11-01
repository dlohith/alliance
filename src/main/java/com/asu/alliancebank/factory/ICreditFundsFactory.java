package com.asu.alliancebank.factory;



import com.asu.alliancebank.controllers.transactionmanagement.backingbean.CreditFundsBackingBean;
import com.asu.alliancebank.domain.impl.CreditFunds;

public interface ICreditFundsFactory {
	public CreditFunds createEmptyCreditFundsObject();
	public CreditFunds createCreditFundsInstance(CreditFundsBackingBean creditFundsBackingBean);
	public CreditFunds createCreditFundsInstance(String accountId, String amount);
}
