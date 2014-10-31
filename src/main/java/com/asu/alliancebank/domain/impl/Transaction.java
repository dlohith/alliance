package com.asu.alliancebank.domain.impl;

import com.asu.alliancebank.domain.ITransaction;

public class Transaction implements ITransaction {
	
	private String transactionId;
		
	private String accountId;
	
	private String transactionType;
	
	private String status;
	
	private Long amount;
	
	private int priority;
	
	public Transaction(){
		
	}
	
	public Transaction(String transactionId ,String accountId,String transactionType,String status,Long amount,int priority){
		this.transactionId = transactionId;
		this.accountId = accountId;
		this.transactionType = transactionType;
		this.status = status;
		this.priority = priority;
		this.amount = amount;
	}
	
	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getAmount() {
		return this.amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}
