package com.asu.alliancebank.domain.impl;



import com.asu.alliancebank.domain.ICreditFunds;

public class CreditFunds implements ICreditFunds{

	private String accountId;	

	private String amount;
	
	public CreditFunds(){
		
	}
	
	public CreditFunds(String accountId , String amount){
		this.accountId = accountId ;
		this.amount = amount ;				
	}
	
	@Override
	public String getAccountId() {
		return this.accountId;
	}

	@Override
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Override
	public String getAmount() {
		return this.amount;
	}

	@Override
	public void setAmount(String amount) {
		this.amount = amount;
	}		
	
}
