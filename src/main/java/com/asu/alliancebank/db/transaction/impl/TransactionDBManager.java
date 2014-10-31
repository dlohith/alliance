package com.asu.alliancebank.db.transaction.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.asu.alliancebank.db.transaction.ITransactionDBManager;
import com.asu.alliancebank.domain.impl.Transaction;

public class TransactionDBManager implements ITransactionDBManager{
	private static final Logger logger = LoggerFactory
			.getLogger(TransactionDBManager.class);
	
	private Connection connection;	
	
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
	public List<Transaction> listAllTransactionLogs(String loggedInUser)
			throws SQLException {
		
		List<Transaction> transactionLogs = new ArrayList<Transaction>();
		Transaction transaction = null;
		String dbCommand;
		String errmsg;
		java.sql.Statement statement;
		dbCommand = "SELECT transactionid , accountid , amount FROM tbl_transaction WHERE accountid = (SELECT accountid FROM tbl_account WHERE loginid = " + loggedInUser + ")";
		//get the connection
		getConnection();
		
		try{
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(dbCommand);
			if(resultSet !=null){ 
				while (resultSet.next()) {
					transaction.setTransactionId(resultSet.getString(0));
					transaction.setAccountId(resultSet.getString(1));
					transaction.setAmount(resultSet.getLong(2));

					transactionLogs.add(transaction);
				} 
			}
		}catch(SQLException e){
			errmsg="DB Issue";
			logger.error("Issue while retrieving transaction list : "+ errmsg,e);			
		}catch(Exception e){
			errmsg="DB Issue";
			logger.error("Issue while retrieving transaction list : "+ errmsg,e);
		}
		finally{
			closeConnection();
		}
		return transactionLogs;
	}
}
