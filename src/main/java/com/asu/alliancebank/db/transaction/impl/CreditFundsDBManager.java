package com.asu.alliancebank.db.transaction.impl;


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
import com.asu.alliancebank.db.transaction.ICreditFundsDBManager;
import com.asu.alliancebank.db.user.impl.UserDBManager;
import com.asu.alliancebank.domain.ITransactionCredit;
import com.asu.alliancebank.domain.impl.CreditFunds;
import com.asu.alliancebank.factory.ICreditFundsFactory;
import com.asu.alliancebank.factory.ITransactionCreditFactory;
import com.asu.alliancebank.service.transaction.ICreditFundsManager;
import com.asu.alliancebank.service.transaction.ITransactionManager;

public class CreditFundsDBManager implements ICreditFundsDBManager{
	private static final Logger logger = LoggerFactory
			.getLogger(UserDBManager.class);
	
	private Connection connection;
	
	@Autowired
	private ICreditFundsFactory creditFundsFactory;
	
	@Autowired
	private ITransactionCreditFactory transactionCreditFactory;
	
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
	public String addCreditFunds(CreditFunds creditFunds, String loggedInUser)
			throws SQLException {
		if(creditFunds == null)
			return "CreditFunds object is null";

		String transactionId = generateUniqueID();
		CallableStatement sqlStatement;
		String errmsg;
		//command to call the SP
		String dbCommand = DBConstants.SP_CALL+ " " + DBConstants.ADD_CREDITFUNDS  + "(?,?,?,?,?,?)";
		getConnection();
		
		//establish the connection with the database
				try{
					Long amount = Long.parseLong(creditFunds.getAmount());
					sqlStatement = connection.prepareCall("{"+dbCommand+"}");
					//adding the input variables to the SP
					sqlStatement.setString(1, transactionId);
					sqlStatement.setString(2, ICreditFundsManager.CREDIT);					  
					sqlStatement.setLong(3, amount);
					sqlStatement.setInt(4, ITransactionManager.SUCCESS);
					sqlStatement.setString(5, loggedInUser);
					//adding output variables to the SP
					sqlStatement.registerOutParameter(6,Types.VARCHAR);
					sqlStatement.execute();
					errmsg = sqlStatement.getString(6);
					return errmsg;
				} catch(SQLException e){
					errmsg="DB Issue";
					logger.error("Issue while adding credit transaction : "+ errmsg,e);			
				} catch(Exception e){
					errmsg="DB Issue";
					logger.error("Issue while adding credit transaction : "+ errmsg,e);
				} finally{
					closeConnection();
				}
				return errmsg;
	}

	@Override
	public List<ITransactionCredit> getCreditDetails(String loggedInUser) throws SQLException {
		
		String dbCommand;
		String errmsg;
		CallableStatement sqlStatement;
		List<ITransactionCredit> creditDetails = new ArrayList<ITransactionCredit>();
		//command to call the SP
		dbCommand = DBConstants.SP_CALL+ " " + DBConstants.GET_CREDIT_DETAILS  + "(?)";
		//get the connection
		getConnection();
		
		try{
			sqlStatement = connection.prepareCall("{"+dbCommand+"}");
			//adding output variables to the SP
			sqlStatement.registerOutParameter(1,Types.VARCHAR);
			sqlStatement.execute();

			ResultSet resultSet = sqlStatement.getResultSet();
			if(resultSet !=null){ 
				while (resultSet.next()) {
					ITransactionCredit creditObject = transactionCreditFactory.createTransactionCredit();
					creditObject.setTransactionId(resultSet.getString(1));
					creditObject.setLoginId(resultSet.getString(2));
					creditObject.setAmount(resultSet.getString(3));
					creditObject.setStatus(getStatusString(resultSet.getInt(4)));
					creditDetails.add(creditObject);
				} 
			}
			
			errmsg = sqlStatement.getString(1);
		}catch(SQLException e){
			errmsg="DB Issue";
			logger.error("Issue while getting credit details : "+ errmsg,e);			
		}catch(Exception e){
			errmsg="DB Issue";
			logger.error("Issue while getting credit details : "+ errmsg,e);
		}
		finally{
			closeConnection();
		}
		return creditDetails;
	}
	
	private String getStatusString(int number) {
		switch(number) {
		case 1: return "PENDING";		
		case 2: return "SUCCESS";
		case 3: return "FAILURE";
		}
		return null;
	}

	@Override
	public List<ITransactionCredit> getCreditDetailsCust(String loggedInUser)
			throws SQLException {
		String dbCommand;
		String errmsg;
		CallableStatement sqlStatement;
		List<ITransactionCredit> creditDetails = new ArrayList<ITransactionCredit>();
		//command to call the SP
		dbCommand = DBConstants.SP_CALL+ " " + DBConstants.GET_CREDIT_DETAILS_CUST  + "(?,?)";
		//get the connection
		getConnection();
		
		try{
			sqlStatement = connection.prepareCall("{"+dbCommand+"}");
			//adding output variables to the SP
			sqlStatement.setString(1, loggedInUser);
			sqlStatement.registerOutParameter(2,Types.VARCHAR);
			sqlStatement.execute();

			ResultSet resultSet = sqlStatement.getResultSet();
			if(resultSet !=null){ 
				while (resultSet.next()) {
					ITransactionCredit creditObject = transactionCreditFactory.createTransactionCredit();
					creditObject.setTransactionId(resultSet.getString(1));
					creditObject.setLoginId(resultSet.getString(2));
					creditObject.setAmount(resultSet.getString(3));
					creditObject.setStatus(getStatusString(resultSet.getInt(4)));
					creditDetails.add(creditObject);
				} 
			}
			
			errmsg = sqlStatement.getString(2);
		}catch(SQLException e){
			errmsg="DB Issue";
			logger.error("Issue while getting credit details : "+ errmsg,e);			
		}catch(Exception e){
			errmsg="DB Issue";
			logger.error("Issue while getting credit details : "+ errmsg,e);
		}
		finally{
			closeConnection();
		}
		return creditDetails;
	}
}
