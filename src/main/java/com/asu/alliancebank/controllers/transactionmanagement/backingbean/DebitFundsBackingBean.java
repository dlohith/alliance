package com.asu.alliancebank.controllers.transactionmanagement.backingbean;



import com.asu.alliancebank.annotation.NotEmpty;
import com.asu.alliancebank.annotation.NotValidDigit;

public class DebitFundsBackingBean {	
	
	@NotEmpty(message = "Please provide amount")	
	@NotValidDigit(message = "Please provide positive amount between 0 and 100000")
	private String amount;
	
	private String accountID;
	
	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public boolean isValid() {
		return true;
	}		

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}	
}
