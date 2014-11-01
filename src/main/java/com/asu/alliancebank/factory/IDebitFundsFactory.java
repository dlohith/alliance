package com.asu.alliancebank.factory;

import com.asu.alliancebank.controllers.transactionmanagement.backingbean.DebitFundsBackingBean;
import com.asu.alliancebank.domain.impl.DebitFunds;

public interface IDebitFundsFactory {
	public DebitFunds createEmptyDebitFundsObject();
	public DebitFunds createDebitFundsInstance(DebitFundsBackingBean debitFundsBackingBean);
	public DebitFunds createDebitFundsInstance(String amount);
}
