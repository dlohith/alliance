package com.asu.alliancebank.factory;

import com.asu.alliancebank.domain.ITransactionCredit;

public interface ITransactionCreditFactory {

	public abstract ITransactionCredit createTransactionCredit();

}