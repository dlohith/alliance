/**
 * 
 */
package com.asu.alliancebank.domain.impl;

import com.asu.alliancebank.domain.ITransactionTransferFund;

/**
 * @author Kedar
 *
 */
public class TransactionTransferFund implements ITransactionTransferFund {

	private String transactionID;
	private String fromUserID;
	private String toUserID;
	private String amount;
	private String status;
	
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public String getFromUserID() {
		return fromUserID;
	}
	public void setFromUserID(String fromUserID) {
		this.fromUserID = fromUserID;
	}
	public String getToUserID() {
		return toUserID;
	}
	public void setToUserID(String toUserID) {
		this.toUserID = toUserID;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
