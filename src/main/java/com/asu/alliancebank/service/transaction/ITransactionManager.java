package com.asu.alliancebank.service.transaction;


public interface ITransactionManager {
	
	public static final int PENDING = 1;
	public static final int SUCCESS = 2;
	public static final int FAILURE = 3;
	
	
	public static final String CREDIT ="CREDIT"; 
	public static final String DEBIT ="DEBIT";
	
	public boolean isValidEncryptedString(String encrypted, String loginId);
}
