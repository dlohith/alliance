package com.asu.alliancebank.domain.impl;

import com.asu.alliancebank.domain.ITransactionCredit;

public class TransactionCredit implements ITransactionCredit {

	
	
	private String transactionId;
	private String amount;
	private String loginId;
	private String status;
	/* (non-Javadoc)
	 * @see com.asu.alliancebank.domain.impl.ITransactionCredit#getTransactionId()
	 */
	@Override
	public String getTransactionId() {
		return transactionId;
	}
	/* (non-Javadoc)
	 * @see com.asu.alliancebank.domain.impl.ITransactionCredit#setTransactionId(java.lang.String)
	 */
	@Override
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	/* (non-Javadoc)
	 * @see com.asu.alliancebank.domain.impl.ITransactionCredit#getAmount()
	 */
	@Override
	public String getAmount() {
		return amount;
	}
	/* (non-Javadoc)
	 * @see com.asu.alliancebank.domain.impl.ITransactionCredit#setAmount(java.lang.String)
	 */
	@Override
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/* (non-Javadoc)
	 * @see com.asu.alliancebank.domain.impl.ITransactionCredit#getLoginId()
	 */
	@Override
	public String getLoginId() {
		return loginId;
	}
	/* (non-Javadoc)
	 * @see com.asu.alliancebank.domain.impl.ITransactionCredit#setLoginId(java.lang.String)
	 */
	@Override
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	/* (non-Javadoc)
	 * @see com.asu.alliancebank.domain.impl.ITransactionCredit#getStatus()
	 */
	@Override
	public String getStatus() {
		return status;
	}
	/* (non-Javadoc)
	 * @see com.asu.alliancebank.domain.impl.ITransactionCredit#setStatus(java.lang.String)
	 */
	@Override
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
