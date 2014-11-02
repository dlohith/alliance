package com.asu.alliancebank.factory;

import com.asu.alliancebank.domain.ITransactionDebit;

public interface ITransactionDebitFactory {

	public abstract ITransactionDebit createTransactionDebit();

}