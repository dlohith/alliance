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
import com.asu.alliancebank.db.transaction.IDebitFundsDBManager;
import com.asu.alliancebank.db.user.impl.UserDBManager;
import com.asu.alliancebank.domain.IDebitFunds;
import com.asu.alliancebank.domain.ITransactionDebit;
import com.asu.alliancebank.domain.impl.DebitFunds;
import com.asu.alliancebank.factory.IDebitFundsFactory;
import com.asu.alliancebank.factory.ITransactionDebitFactory;
import com.asu.alliancebank.service.transaction.IDebitFundsManager;
import com.asu.alliancebank.service.transaction.ITransactionManager;

public class DebitFundsDBManager implements IDebitFundsDBManager{
	private static final Logger logger = LoggerFactory
			.getLogger(UserDBManager.class);
	
	private Connection connection;
	
	@Autowired
	private IDebitFundsFactory debitFundsFactory;
	
	@Autowired
	private ITransactionDebitFactory transactionDebitFactory;
	
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
	public IDebitFunds getDebitFundsToFinalize(String transactionId)throws SQLException{
		if(transactionId.isEmpty())
			return null;
		
		IDebitFunds debitFunds = debitFundsFactory.createEmptyDebitFundsObject();
		String dbCommand;
		String errmsg;
		CallableStatement sqlStatement;
		
		//command to call the SP
		dbCommand = DBConstants.SP_CALL+ " " + DBConstants.GET_TRANS_DEBIT_FINAL_DETAIL  + "(?,?)";
		//get the connection
		getConnection();
		//establish the connection with the database
		try{
			sqlStatement = connection.prepareCall("{"+dbCommand+"}");
			//adding the input variables to the SP
			sqlStatement.setString(1, transactionId);
			
			
			//adding output variables to the SP
			sqlStatement.registerOutParameter(2,Types.VARCHAR);
			sqlStatement.execute();

			ResultSet resultSet = sqlStatement.getResultSet();
			if(resultSet !=null){ 
				while (resultSet.next()) {
					
					debitFunds.setAccountId(resultSet.getString(1));
					debitFunds.setAmount(resultSet.getString(2));
				} 
			}
			return debitFunds;

		}catch(SQLException e){
			errmsg="DB Issue";
			logger.error("Issue while debit : "+ errmsg,e);			
		}catch(Exception e){
			errmsg="DB Issue";
			logger.error("Issue while debit : "+ errmsg,e);
		}
		finally{
			closeConnection();
		}
		return debitFunds;
	}
	
	@Override
	public boolean isTransactionHashCorrect( String transactionId, String hashValue)throws SQLException{
		
		if(transactionId.isEmpty())
			return false;
		
		String dbCommand;
		String errmsg;
		CallableStatement sqlStatement;
		
		//command to call the SP
		dbCommand = DBConstants.SP_CALL+ " " + DBConstants.IS_TRANS_HASH_CORRECT_DEBIT  + "(?,?,?)";
		//get the connection
		getConnection();
		//establish the connection with the database
		try{
			sqlStatement = connection.prepareCall("{"+dbCommand+"}");
			//adding the input variables to the SP
			sqlStatement.setString(1, transactionId);
			sqlStatement.setString(2, hashValue);
			
			//adding output variables to the SP
			sqlStatement.registerOutParameter(3,Types.VARCHAR);
			sqlStatement.execute();

			ResultSet resultSet = sqlStatement.getResultSet();
			if(resultSet !=null){ 
				while (resultSet.next()) {
					
					return true;
				} 
			}
			return false;

		}catch(SQLException e){
			errmsg="DB Issue";
			logger.error("Issue while debit : "+ errmsg,e);			
		}catch(Exception e){
			errmsg="DB Issue";
			logger.error("Issue while debit: "+ errmsg,e);
		}
		finally{
			closeConnection();
		}
		return false;
	}
	
	@Override
	public String finalizeTransactionDebit( String transactionId,IDebitFunds debitFunds, String loggedInUser)throws SQLException{
		
		
		String dbCommand;
		String errmsg;
		CallableStatement sqlStatement;
		
		//command to call the SP
		dbCommand = DBConstants.SP_CALL+ " " + DBConstants.FINALIZE_TRANS_DEBIT  + "(?,?,?,?,?,?)";
		//get the connection
		getConnection();
		//establish the connection with the database
		try{
			sqlStatement = connection.prepareCall("{"+dbCommand+"}");
			double amount =  Double.parseDouble(debitFunds.getAmount());
			//adding the input variables to the SP
			sqlStatement.setString(1, transactionId);
			sqlStatement.setString(2, debitFunds.getAccountId());
			sqlStatement.setDouble(3, amount);
			sqlStatement.setInt(4, ITransactionManager.SUCCESS);
			sqlStatement.setString(5, loggedInUser);
			
			
			
			//adding output variables to the SP
			sqlStatement.registerOutParameter(6,Types.VARCHAR);
			sqlStatement.execute();

			errmsg = sqlStatement.getString(6);
			return errmsg;

		}catch(SQLException e){
			errmsg="DB Issue";
			logger.error("Issue while debit : "+ errmsg,e);			
		}catch(Exception e){
			errmsg="DB Issue";
			logger.error("Issue while debit : "+ errmsg,e);
		}
		finally{
			closeConnection();
		}
		return errmsg;
	}
	
	
	@Override
	public String addTransactionDebitHash(String hashValue, String loggedInUser,String transactionId) throws SQLException{
		if(hashValue == null)
			return "hashValue object is null";

		CallableStatement sqlStatement;
		String errmsg;
		//command to call the SP
		String dbCommand = DBConstants.SP_CALL+ " " + DBConstants.TRANS_DEBIT_HASH  + "(?,?,?,?)";
		getConnection();
		
		//establish the connection with the database
				try{
					sqlStatement = connection.prepareCall("{"+dbCommand+"}");
					//adding the input variables to the SP
					sqlStatement.setString(1, transactionId);
					sqlStatement.setString(2, hashValue);					  
					sqlStatement.setString(3, loggedInUser);
					//adding output variables to the SP
					sqlStatement.registerOutParameter(4,Types.VARCHAR);
					sqlStatement.execute();
					errmsg = sqlStatement.getString(4);
					return errmsg;
				} catch(SQLException e){
					errmsg="DB Issue";
					logger.error("Issue while adding debit transaction : "+ errmsg,e);			
				} catch(Exception e){
					errmsg="DB Issue";
					logger.error("Issue while adding debit transaction : "+ errmsg,e);
				} finally{
					closeConnection();
				}
				return errmsg;
	}
	
	@Override
	public String addDebitFunds(DebitFunds debitFunds, String loggedInUser,String transactionId)
			throws SQLException {
		if(debitFunds == null)
			return "DebitFunds object is null";

		String transactionType = IDebitFundsManager.DEBIT;
		int status = ITransactionManager.SUCCESS;
								
		CallableStatement sqlStatement;
		String errmsg;
		//command to call the SP
		String dbCommand = DBConstants.SP_CALL+ " " + DBConstants.ADD_DEBITFUNDS  + "(?,?,?,?,?,?)";
		getConnection();
		
		//establish the connection with the database
				try{
					double amount = Double.parseDouble(debitFunds.getAmount());
					sqlStatement = connection.prepareCall("{"+dbCommand+"}");
					//adding the input variables to the SP
					sqlStatement.setString(1, transactionId);
					sqlStatement.setString(2, transactionType);					  
					sqlStatement.setDouble(3, amount);
					sqlStatement.setInt(4, ITransactionManager.PENDING);
					sqlStatement.setString(5, loggedInUser);
					
					//adding output variables to the SP
					sqlStatement.registerOutParameter(6,Types.VARCHAR);
					sqlStatement.execute();
					errmsg = sqlStatement.getString(6);
					return errmsg;
				} catch(SQLException e){
					errmsg="DB Issue";
					logger.error("Issue while adding debit transaction : "+ errmsg,e);			
				} catch(Exception e){
					errmsg="DB Issue";
					logger.error("Issue while adding debit transaction : "+ errmsg,e);
				} finally{
					closeConnection();
				}
				return errmsg;
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
	public List<ITransactionDebit> getDebitDetails(String loggedInUser) throws SQLException {
		String dbCommand;
		String errmsg;
		CallableStatement sqlStatement;
		List<ITransactionDebit> debitDetails = new ArrayList<ITransactionDebit>();
		//command to call the SP
		dbCommand = DBConstants.SP_CALL+ " " + DBConstants.GET_DEBIT_DETAILS  + "(?)";
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
					ITransactionDebit debitObject = transactionDebitFactory.createTransactionDebit();
					debitObject.setTransactionId(resultSet.getString(1));
					debitObject.setLoginId(resultSet.getString(2));
					debitObject.setAmount(resultSet.getString(3));
					debitObject.setStatus(getStatusString(resultSet.getInt(4)));
					debitDetails.add(debitObject);
				} 
			}
			
			errmsg = sqlStatement.getString(1);
		}catch(SQLException e){
			errmsg="DB Issue";
			logger.error("Issue while getting debit details : "+ errmsg,e);			
		}catch(Exception e){
			errmsg="DB Issue";
			logger.error("Issue while getting debit details : "+ errmsg,e);
		}
		finally{
			closeConnection();
		}
		return debitDetails;
	}

	@Override
	public List<ITransactionDebit> getDebitDetailsCust(String loggedInUser)
			throws SQLException {
		String dbCommand;
		String errmsg;
		CallableStatement sqlStatement;
		List<ITransactionDebit> debitDetails = new ArrayList<ITransactionDebit>();
		//command to call the SP
		dbCommand = DBConstants.SP_CALL+ " " + DBConstants.GET_DEBIT_DETAILS_CUST  + "(?,?)";
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
					ITransactionDebit debitObject = transactionDebitFactory.createTransactionDebit();
					debitObject.setTransactionId(resultSet.getString(1));
					debitObject.setLoginId(resultSet.getString(2));
					debitObject.setAmount(resultSet.getString(3));
					debitObject.setStatus(getStatusString(resultSet.getInt(4)));
					debitDetails.add(debitObject);
				} 
			}
			
			errmsg = sqlStatement.getString(2);
		}catch(SQLException e){
			errmsg="DB Issue";
			logger.error("Issue while getting debit details : "+ errmsg,e);			
		}catch(Exception e){
			errmsg="DB Issue";
			logger.error("Issue while getting debit details : "+ errmsg,e);
		}
		finally{
			closeConnection();
		}
		return debitDetails;
	}
	
}
