package com.asu.alliancebank.domain.impl;

/**
 * @author Sravya
 *
 */

import com.asu.alliancebank.domain.ITransferFunds;

public class TransferFunds implements ITransferFunds{

	private String fromAccountId;	

	private String toAccountId;	

	private Long amount;
	
	public TransferFunds(){
		
	}
	
	public TransferFunds(String fromAccountId , String toAccountId , Long amount){
		this.fromAccountId = fromAccountId ;
		this.toAccountId = toAccountId ;
		this.amount = amount ;				
	}
	
	public String getFromAccountId() {
		return this.fromAccountId;
	}

	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	
//	public List<String> getToAccountIdList() {
//		List<String> accountIdList = new ArrayList<String>();
//		if(accountIdList != null){
//			accountIdList.add(getToAccountId());
//		}
//		
//		return accountIdList;
//
//	}
	
	public String getToAccountId(){
		return this.toAccountId;
	}

	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}
	
	public Long getAmount() {
		return this.amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}		
	
}
