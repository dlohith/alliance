/**
 * 
 */
package com.asu.alliancebank.domain;

/**
 * @author Kedar
 *
 */
public interface IMerchantRequest {

	public abstract String getRequestID();
	public abstract void setRequestID(String requestID);
	public abstract String getMerchantID() ;
	public abstract void setMerchantID(String merchantID);
	public abstract String getUserLoginID();
	public abstract void setUserLoginID(String userLoginID);
	public abstract String getAmount() ;
	public abstract void setAmount(String amount) ;
	public abstract int getStatus();
	public abstract void setStatus(int status);
}
