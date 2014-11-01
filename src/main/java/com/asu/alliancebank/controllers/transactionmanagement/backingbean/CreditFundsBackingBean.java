package com.asu.alliancebank.controllers.transactionmanagement.backingbean;



import com.asu.alliancebank.annotation.NotEmpty;
import com.asu.alliancebank.annotation.NotValidDigit;

public class CreditFundsBackingBean {

	private String accountID;
	
	@NotEmpty(message = "Please provide amount")	
	@NotValidDigit(message = "Please provide only integer values > 0 and < 100000")
	//check if only numbers are present
	private String amount;
	
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
