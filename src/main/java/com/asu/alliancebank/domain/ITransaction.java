package com.asu.alliancebank.domain;

public interface ITransaction {

	public abstract String getTransactionId();
	public abstract void setTransactionId(String transactionId);
	public abstract String getAccountId();
	public abstract void setAccountId(String accountId);
	public abstract String getTransactionType();
	public abstract void setTransactionType(String transactionType);
	public abstract String getStatus();
	public abstract void setStatus(String status);
	public abstract Long getAmount();
	public abstract void setAmount(Long amount);
	public abstract int getPriority();
	public abstract void setPriority(int priority);
}
