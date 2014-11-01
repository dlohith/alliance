/**
 * 
 */
package com.asu.alliancebank.domain.impl;

import com.asu.alliancebank.domain.IMerchantRequest;

/**
 * @author Kedar
 *
 */
public class MerchantRequest implements IMerchantRequest {
	
	private String requestID;
	private String merchantID;
	private String userLoginID;
	private String amount;
	private int status;
	
	@Override
	public String getRequestID() {
		return requestID;
	}
	
	@Override
	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}
	
	@Override
	public String getMerchantID() {
		return merchantID;
	}
	
	@Override
	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}
	
	@Override
	public String getUserLoginID() {
		return userLoginID;
	}
	
	@Override
	public void setUserLoginID(String userLoginID) {
		this.userLoginID = userLoginID;
	}
	public String getAmount() {
		return amount;
	}
	
	@Override
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	@Override
	public int getStatus() {
		return status;
	}
	
	@Override
	public void setStatus(int status) {
		this.status = status;
	}
	
}
