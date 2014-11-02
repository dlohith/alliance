/**
 * 
 */
package com.asu.alliancebank.domain;

/**
 * @author Kedar
 *
 */
public interface ITransactionTransferFund {
	public abstract String getTransactionID();
	public abstract void setTransactionID(String transactionID);
	public abstract String getFromUserID();
	public abstract void setFromUserID(String fromUserID);
	public abstract String getToUserID();
	public abstract void setToUserID(String toUserID);
	public abstract String getAmount();
	public abstract void setAmount(String toUserID);
	public abstract String getStatus();
	public abstract void setStatus(String toUserID);
}
