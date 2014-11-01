package com.asu.alliancebank.domain.impl;



import com.asu.alliancebank.domain.IDebitFunds;

public class DebitFunds implements IDebitFunds{
	

	private String amount;
	
	public DebitFunds(){
		
	}
	
	public DebitFunds( String amount){		
		this.amount = amount ;				
	}
	
	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}		
	
}
