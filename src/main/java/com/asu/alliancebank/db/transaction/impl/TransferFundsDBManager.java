package com.asu.alliancebank.db.transaction.impl;
/**
 * @author Sravya
 *
 */
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
import com.asu.alliancebank.db.transaction.ITransferFundsDBManager;
import com.asu.alliancebank.db.user.impl.UserDBManager;
import com.asu.alliancebank.domain.impl.TransferFunds;
import com.asu.alliancebank.factory.ITransferFundsFactory;

public class TransferFundsDBManager implements ITransferFundsDBManager{
	private static final Logger logger = LoggerFactory
			.getLogger(UserDBManager.class);
	
	private Connection connection;
	
	@Autowired
	private ITransferFundsFactory transferFundsFactory;
	
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
	
	private String generateUniqueID()
	{
		return UUID.randomUUID().toString();
	}
	
	@Override
	public List<String> getAllUserNames(String loggedInUser)
			throws SQLException {
		
		List<String> userNames = new ArrayList<String>();
		String dbCommand;
		String errmsg;
		java.sql.Statement statement;
		dbCommand = "SELECT firstname , lastname , loginid FROM tbl_user";
		//get the connection
		getConnection();
		
		try{
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(dbCommand);
			if(resultSet !=null){ 
				while (resultSet.next()) {
					if(resultSet.getString(2) != loggedInUser)
					userNames.add(resultSet.getString(0) +"," + resultSet.getString(1));
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
		return userNames;
	}

	
	@Override
	public String addTransferFunds(TransferFunds transferFunds, String loggedInUser)
			throws SQLException {
		if(transferFunds == null)
			return "TransferFunds object is null";

		String transactionId = generateUniqueID();
		String fromAccountId = transferFunds.getFromAccountId();
		String toAccountId = transferFunds.getToAccountId();	
		Long amount = transferFunds.getAmount();	
		
		//check if the user id has his account id in the fromaccountid section.					
		
		CallableStatement sqlStatement;
		String errmsg;
		//command to call the SP
		String dbCommand = DBConstants.SP_CALL+ " " + DBConstants.ADD_TRANSFERFUNDS  + "(?,?,?,?,?)";
		getConnection();
		
		//establish the connection with the database
				try{
					sqlStatement = connection.prepareCall("{"+dbCommand+"}");
					//adding the input variables to the SP
					sqlStatement.setString(1, transactionId);
					sqlStatement.setString(2, fromAccountId);					  
					sqlStatement.setString(3, toAccountId);
					sqlStatement.setLong(4, amount);
					//sqlStatement.setInt(5, priority);
					sqlStatement.setString(5, loggedInUser);
					
					//adding output variables to the SP
					sqlStatement.registerOutParameter(6,Types.VARCHAR);
					sqlStatement.execute();
					errmsg = sqlStatement.getString(6);
					return errmsg;
				} catch(SQLException e){
					errmsg="DB Issue";
					logger.error("Issue while adding transaction : "+ errmsg,e);			
				} catch(Exception e){
					errmsg="DB Issue";
					logger.error("Issue while adding transaction : "+ errmsg,e);
				} finally{
					closeConnection();
				}
				return errmsg;
	}		
	
}
