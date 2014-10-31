package com.asu.alliancebank.controllers.transactionmanagement.backingbean;

import com.asu.alliancebank.annotation.NotEmpty;

public class TransferFundsBackingBean {
	
	public TransferFundsBackingBean(String fromAccountId ){
		this.fromAccountId = fromAccountId;
	}
	
	private String fromAccountId;	

	@NotEmpty(message = "Please provide to accountid")
	private String toAccountId;
	
	@NotEmpty(message = "Please provide amount")	
	//check if only numbers are present
	private Long amount;
	
	public boolean isValid() {
		return true;
	}	
	
	public String getFromAccountId() {
		return this.fromAccountId;
	}

	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public String getToAccountId() {
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
	
//	public List<String> getToAccountIds(){
//		List<String> accountIdList = new ArrayList<String>();
//		if(accountIdList != null){
//			accountIdList.add(getToAccountId());
//		}
//		
//		return accountIdList;
//
//	}
	

}
