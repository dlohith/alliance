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
	
}
