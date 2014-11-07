package com.asu.alliancebank.domain;
/**
 * @author Sravya
 *
 */

public interface ITransferFunds {
	public abstract String getFromAccountId();
	
	public abstract void setFromAccountId(String fromAccountId);
	
	//public abstract List<String> getToAccountIdList();
	
	public abstract void setToAccountId(String toAccountId);
	
	public abstract String getToAccountId();
	
	public abstract double getAmount();
	
	public abstract void setAmount(double amount);
	
}
