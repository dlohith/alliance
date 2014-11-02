/**
 * 
 */
package com.asu.alliancebank.db.account.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.asu.alliancebank.db.DBConstants;
import com.asu.alliancebank.db.account.IAccountDBManager;
import com.asu.alliancebank.db.user.impl.UserDBManager;
import com.asu.alliancebank.domain.impl.Account;
import com.asu.alliancebank.factory.IAccountFactory;

/**
 * @author Kedar
 *
 */
public class AccountDBManager implements IAccountDBManager {
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserDBManager.class);
	
	private Connection connection;
	
	@Autowired
	private IAccountFactory accountFactory;
	
	@Autowired
	private DataSource dataSource;
	
	/**
	 * Assigns the data source
	 *  
	 *  @param : dataSource
	 */
	public void setDataSource(DataSource dataSource) 
	{
		this.dataSource = dataSource;
	}
	
	/**
	 * Establishes connection with the  DB
	 * @return      connection handle for the created connection
	 * @throws      Exception
	 */
	private void getConnection() throws SQLException {
		try
		{
			connection = dataSource.getConnection();
		}
		catch(SQLException e)
		{
			logger.error("Exception in getConnection():",e);
			throw new SQLException(e);
		}
	}
	
	/**
	 * Close the DB connection
	 * 
	 * @return : 0 on success
	 *           -1 on failure
	 *           
	 * @throws : SQL Exception          
	 */
	private int closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
			return 0;
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		return 1;
	}

	@Override
	public String addAccount(Account account, String loggedInUser) throws SQLException {
		
		if(account == null)
			return "Account object is null";

		String userid = account.getUserID();
		Long balance = account.getBalance();
		String accountid = generateUniqueID();
		
		CallableStatement sqlStatement;
		String errmsg;
		//command to call the SP
		String dbCommand = DBConstants.SP_CALL+ " " + DBConstants.ADD_ACCOUNT  + "(?,?,?,?,?)";
		getConnection();
		
		//establish the connection with the database
				try{
					sqlStatement = connection.prepareCall("{"+dbCommand+"}");
					//adding the input variables to the SP
					sqlStatement.setString(1, accountid);
					sqlStatement.setString(2, userid);        	
					sqlStatement.setLong(3, balance);
					sqlStatement.setString(4, loggedInUser);
					
					//adding output variables to the SP
					sqlStatement.registerOutParameter(5,Types.VARCHAR);
					sqlStatement.execute();
					errmsg = sqlStatement.getString(5);
					return errmsg;
				} catch(SQLException e){
					errmsg="DB Issue";
					logger.error("Issue while adding account : "+ errmsg,e);			
				} catch(Exception e){
					errmsg="DB Issue";
					logger.error("Issue while adding account : "+ errmsg,e);
				} finally{
					closeConnection();
				}
				return errmsg;
	}
	
	private String generateUniqueID()
	{
		return UUID.randomUUID().toString();
	}

	@Override
	public Account getAccount(String accountID) throws SQLException {
		Account account = null;
		String dbCommand;
		String errmsg;
		CallableStatement sqlStatement;
		
		//command to call the SP
		dbCommand = DBConstants.SP_CALL+ " " + DBConstants.GET_ACCOUNT  + "(?,?)";
		//get the connection
		getConnection();
		
		try{
			sqlStatement = connection.prepareCall("{"+dbCommand+"}");
			//adding the input variables to the SP
			sqlStatement.setString(1, accountID);
			
			//adding output variables to the SP
			sqlStatement.registerOutParameter(2,Types.VARCHAR);
			sqlStatement.execute();

			ResultSet resultSet = sqlStatement.getResultSet();
			if(resultSet !=null){ 
				while (resultSet.next()) {
					account = accountFactory.createEmptyAccountObject();
					account.setAccountID(resultSet.getString(1));
					account.setUserID(resultSet.getString(2));
					account.setBalance(resultSet.getLong(3));
					break;
				} 
			}
			
			errmsg = sqlStatement.getString(2);
			return account;
		}catch(SQLException e){
			errmsg="DB Issue";
			logger.error("Issue while adding account : "+ errmsg,e);			
		}catch(Exception e){
			errmsg="DB Issue";
			logger.error("Issue while adding account : "+ errmsg,e);
		}
		finally{
			closeConnection();
		}
		return account;
	}

	@Override
	public String deleteAccount(String accountID) throws SQLException {
		String dbCommand;
		String errmsg;
		CallableStatement sqlStatement;
		
		//command to call the SP
		dbCommand = DBConstants.SP_CALL+ " " + DBConstants.DELETE_ACCOUNT  + "(?,?)";
		//get the connection
		getConnection();
		
		try{
			sqlStatement = connection.prepareCall("{"+dbCommand+"}");
			//adding the input variables to the SP
			sqlStatement.setString(1, accountID);
			
			//adding output variables to the SP
			sqlStatement.registerOutParameter(2,Types.VARCHAR);
			sqlStatement.execute();

			sqlStatement.getResultSet();
			errmsg = sqlStatement.getString(2);
		}catch(SQLException e){
			errmsg="DB Issue";
			logger.error("Issue while deleting account : "+ errmsg,e);			
		}catch(Exception e){
			errmsg="DB Issue";
			logger.error("Issue while deleting account : "+ errmsg,e);
		}
		finally{
			closeConnection();
		}
		return errmsg;
	}

	@Override
	public List<Account> getAllAccounts() throws SQLException {
		List<Account> accounts = new ArrayList<Account>();
		String dbCommand;
		String errmsg;
		CallableStatement sqlStatement;
		
		//command to call the SP
		dbCommand = DBConstants.SP_CALL+ " " + DBConstants.LIST_ALL_ACCOUNT  + "(?)";
		//get the connection
		getConnection();
		//establish the connection with the database
		try{
			sqlStatement = connection.prepareCall("{"+dbCommand+"}");
			//adding output variables to the SP
			sqlStatement.registerOutParameter(1,Types.VARCHAR);
			sqlStatement.execute();

			ResultSet resultSet = sqlStatement.getResultSet();
			if(resultSet !=null){ 
				while (resultSet.next()) {
					Account account = accountFactory.createEmptyAccountObject();
					account.setAccountID(resultSet.getString(1));
					account.setUserID(resultSet.getString(2));
					account.setBalance(resultSet.getLong(3));
					accounts.add(account);
				} 
			}
			
			errmsg = sqlStatement.getString(1);
			return accounts;
		}catch(SQLException e){
			errmsg="DB Issue";
			logger.error("Issue while getting accounts : "+ errmsg,e);			
		}catch(Exception e){
			errmsg="DB Issue";
			logger.error("Issue while getting accounts : "+ errmsg,e);
		}
		finally{
			closeConnection();
		}
		return accounts;
	}
	
	
	@Override
	public boolean hasAccount(String loggedinId) throws SQLException {
		boolean result = false;
		String dbCommand;
		String errmsg;
		CallableStatement sqlStatement;
		
		//command to call the SP
		dbCommand = DBConstants.SP_CALL+ " " + DBConstants.HAS_ACCOUNT  + "(?,?)";
		//get the connection
		getConnection();
		//establish the connection with the database
		try{
			sqlStatement = connection.prepareCall("{"+dbCommand+"}");
			sqlStatement.setString(1, loggedinId);
			
			//adding output variables to the SP
			sqlStatement.registerOutParameter(2,Types.VARCHAR);
			sqlStatement.execute();

			ResultSet resultSet = sqlStatement.getResultSet();
			if(resultSet !=null){ 
				while (resultSet.next()) {
					String out = resultSet.getString(1);
					if(out.equals("1")){
						return true;
					}
				} 
			}
			
			errmsg = sqlStatement.getString(2);
			return result;
		}catch(SQLException e){
			errmsg="DB Issue";
			logger.error("Issue while getting accounts : "+ errmsg,e);			
		}catch(Exception e){
			errmsg="DB Issue";
			logger.error("Issue while getting accounts : "+ errmsg,e);
		}
		finally{
			closeConnection();
		}
		return result;
	}

	@Override
	public List<String> getUserLoginIDsForListAccount(String loggedInUser)
			throws SQLException {
		List<String> loginIDList = new ArrayList<String>();
		String dbCommand;
		String errmsg;
		java.sql.Statement statement;
		dbCommand = "SELECT loginid FROM tbl_user WHERE loginid IN (SELECT userid from tbl_account)";
		//get the connection
		getConnection();
		
		try{
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(dbCommand);
			if(resultSet !=null){ 
				while (resultSet.next()) {
					loginIDList.add(resultSet.getString(1));
				} 
			}
		}catch(SQLException e){
			errmsg="DB Issue";
			logger.error("Issue while getting login ID list : "+ errmsg,e);			
		}catch(Exception e){
			errmsg="DB Issue";
			logger.error("Issue while getting login ID list : "+ errmsg,e);
		}
		finally{
			closeConnection();
		}
		return loginIDList;
	}

	@Override
	public String getAccountBalance(String loggedInUser) throws SQLException {
		String balance = new String("0");
		String dbCommand;
		String errmsg;
		CallableStatement sqlStatement;
		
		//command to call the SP
		dbCommand = DBConstants.SP_CALL+ " " + DBConstants.GET_ACC_BALANCE  + "(?,?)";
		//get the connection
		getConnection();
		//establish the connection with the database
		try{
			sqlStatement = connection.prepareCall("{"+dbCommand+"}");
			sqlStatement.setString(1, loggedInUser);
			//adding output variables to the SP
			sqlStatement.registerOutParameter(2,Types.VARCHAR);
			sqlStatement.execute();

			ResultSet resultSet = sqlStatement.getResultSet();
			if(resultSet !=null){ 
				while (resultSet.next()) {
					balance = resultSet.getString(1);
				} 
			}
			else {
				// if result set is null then set balance as 0;
				balance = "0";
			}
			errmsg = sqlStatement.getString(2);
		}catch(SQLException e){
			errmsg="DB Issue";
			balance = "0";
			logger.error("Issue while getting account balance : "+ errmsg,e);			
		}catch(Exception e){
			errmsg="DB Issue";
			balance = "0";
			logger.error("Issue while getting account balance : "+ errmsg,e);
		}
		finally{
			closeConnection();
		}
		return balance;
	}

}
