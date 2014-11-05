package com.asu.alliancebank.domain;



public interface IDebitFunds {
	
	public abstract String getAmount();
	
	public abstract void setAmount(String amount);
	
	public void setAccountId(String accountId);
	public String getAccountId();
	
}
