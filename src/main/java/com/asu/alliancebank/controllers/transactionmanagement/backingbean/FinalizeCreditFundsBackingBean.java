package com.asu.alliancebank.controllers.transactionmanagement.backingbean;



import com.asu.alliancebank.annotation.NotEmpty;
import com.asu.alliancebank.annotation.NotInvalidEncryptString;

public class FinalizeCreditFundsBackingBean {

	private String accountID;

	private String amount;
	
	@NotEmpty(message = "Please provide encrypted string from client pki helper using use private key")
	@NotInvalidEncryptString
	private String encrypt;
	
	public String getEncrypt() {
		return encrypt;
	}

	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}

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
