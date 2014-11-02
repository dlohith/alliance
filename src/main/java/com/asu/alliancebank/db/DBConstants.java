package com.asu.alliancebank.db;

public class DBConstants {

	public final static String SP_CALL = "call";
	
	//User operations
	public final static String ADD_USER = "sp_adduser";
	public final static String LIST_ALL_USER = "sp_getAllUsers";
	public final static String GET_USER = "sp_getUser";
	public final static String IS_LOGIN_UNIQUE = "sp_isLoginUnique";
	public final static String DELETE_USER = "sp_deleteuser";
	public final static String MODIFY_USER = "sp_modifyuser";
	
	//Account operations
	public final static String ADD_ACCOUNT = "sp_addAccount";
	public final static String LIST_ALL_ACCOUNT = "sp_getAllAccounts";
	public final static String GET_ACCOUNT = "sp_getAccount";
	public final static String DELETE_ACCOUNT = "sp_deleteAccount";
	public final static String GET_ACC_BALANCE = "sp_getAccountBalance";
	public final static String HAS_ACCOUNT = "sp_hasAccount";
	
	// Transaction operations
	public final static String ADD_TRANSFERFUNDS ="sp_transferFunds";
	public final static String ADD_CREDITFUNDS = "sp_creditFunds";
	public final static String ADD_DEBITFUNDS = "sp_debitFunds";
	public final static String ADD_MERCHANTREQUEST = "sp_addmerchantrequest";
	public final static String AUTHORIZE_PAYMENTS = "sp_authorizepayments";
	public final static String GET_MERCHANT_REQUEST = "sp_getmerchantrequest";
	public final static String GET_ALL_MERCHANT_REQUESTS = "sp_getallmerchantrequests";
	public final static String GET_ALL_MERCHANT_REQUESTS_MER = "sp_getallmerchantrequestsmer";
	public final static String REJECT_MERCHANT_REQUEST = "sp_rejectmerchantrequest";
	public final static String GET_PENDING_MERCHANT_REQUESTS = "sp_getpendingmerchantrequests";
	public final static String GET_CREDIT_DETAILS = "sp_getcreditdetails";
	public final static String GET_DEBIT_DETAILS = "sp_getdebitdetails";
	public final static String GET_CREDIT_DETAILS_CUST = "sp_getcreditdetailscust";
	public final static String GET_DEBIT_DETAILS_CUST = "sp_getdebitdetailscust";
	public final static String UPDATE_TRANSFERFUNDS = "sp_otpTransferFunds";
	public final static String GET_TRANSFERFUNDS = "sp_getTransferFunds";

	
	// OTP operations
	public final static String ADD_TRANSACTION_OTP = "sp_addtransactionotp";
	public final static String  OTP_EXPIRE = "sp_otpexpire";
	
}
