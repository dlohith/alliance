/**
 * 
 */
package com.asu.alliancebank.controllers.transactionmanagement.backingbean;

import com.asu.alliancebank.annotation.NotEmpty;
import com.asu.alliancebank.annotation.NotValidDigit;

/**
 * @author Kedar
 *
 */
public class MerchantDebitFundsBackingBean {
	@NotEmpty(message = "Please provide amount")	
	@NotValidDigit(message = "Please provide only integer values > 0 and < 100000")
	//check if only numbers are present
	private String amount;
	
	private String userLoginID;
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getUserLoginID() {
		return userLoginID;
	}

	public void setUserLoginID(String userLoginID) {
		this.userLoginID = userLoginID;
	}
}
