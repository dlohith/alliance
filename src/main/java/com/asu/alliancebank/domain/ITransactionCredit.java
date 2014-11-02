package com.asu.alliancebank.domain;

public interface ITransactionCredit {

	public abstract String getTransactionId();

	public abstract void setTransactionId(String transactionId);

	public abstract String getAmount();

	public abstract void setAmount(String amount);

	public abstract String getLoginId();

	public abstract void setLoginId(String loginId);

	public abstract String getStatus();

	public abstract void setStatus(String status);

}