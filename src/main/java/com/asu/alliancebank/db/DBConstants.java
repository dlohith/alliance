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
	public final static String UPDATE_FAILED_LOGIN_ATTEMPTS = "sp_updateFailedAttempts";
	public final static String RESET_FAILED_LOGIN_ATTEMPTS = "sp_resetFailedAttempts";
	public final static String UNLOCK_USER = "sp_unlockUser";
	public final static String FIRST_TIME_LOGIN = "sp_isFirstLogin";
	public final static String CHANGE_PASS = "sp_changePass";
	
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
	public final static String GET_TRANSFERFUNDS_DETAILS = "sp_getrransferdunddetails";
	public final static String GET_TRANSFERFUNDS_DETAILS_CUST = "sp_getrransferdunddetailscust";
	public final static String GET_USERID_MERCHATREQ = "sp_getuseridformerchantrequest";
	public final static String GET_AMOUNT_MERCHATREQ = "sp_getamountformerchantrequest";
	
	

	public final static String TRANS_CREDIT_HASH = "sp_transactioncredithash";
	public final static String IS_TRANS_HASH_CORRECT_CREDIT = "sp_transactioncredithashcheck";
	public final static String GET_TRANS_CREDIT_FINAL_DETAIL = "sp_getCreditFundFinalDetails";
	public final static String FINALIZE_TRANS_CREDIT = "sp_finalizeCreditFunds";
	
	public final static String TRANS_DEBIT_HASH = "sp_transactiondebithash";
	public final static String IS_TRANS_HASH_CORRECT_DEBIT = "sp_transactiondebithashcheck";
	public final static String GET_TRANS_DEBIT_FINAL_DETAIL = "sp_getDebitFundFinalDetails";
	public final static String FINALIZE_TRANS_DEBIT = "sp_finalizeDebitFunds";

	public final static String ADD_CUST_AUTH_REQUEST = "sp_addcustauthrequest";
	public final static String GET_CUST_AUTH_REQUEST = "sp_getcustauthrequests";
	public final static String AUTHORIZE_CUST_REQUEST = "sp_addauthorizerequest";
	public final static String GET_PENDING_MERCHANT_REQUESTS_EMPL = "sp_getauthorizerrequestsforemployee";

	
	// OTP operations
	public final static String ADD_TRANSACTION_OTP = "sp_addtransactionotp";
	public final static String  OTP_EXPIRE = "sp_otpexpire";
	
}
