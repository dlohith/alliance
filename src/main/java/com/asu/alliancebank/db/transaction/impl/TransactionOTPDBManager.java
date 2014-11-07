package com.asu.alliancebank.db.transaction.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.asu.alliancebank.db.DBConstants;
import com.asu.alliancebank.db.transaction.ITransactionOTPDBManager;
import com.asu.alliancebank.db.user.impl.UserDBManager;
import com.asu.alliancebank.domain.impl.TransferFunds;
import com.asu.alliancebank.service.transaction.ITransactionManager;

public class TransactionOTPDBManager implements ITransactionOTPDBManager{

	private static final Logger logger = LoggerFactory
			.getLogger(UserDBManager.class);
	
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
	public boolean isTransactionOtpCorrect(String transactionId, String otp)throws SQLException
	{
		if(transactionId==null || otp == null || transactionId.isEmpty() || otp.isEmpty()) {
			return false ;
		}
		
		String dbCommand;
		String errmsg;
		CallableStatement sqlStatement;
		
		//command to call the SP
		dbCommand = DBConstants.SP_CALL+ " " + DBConstants.OTP_EXPIRE  + "(?,?,?)";
		
		//get the connection
		getConnection();
		//establish the connection with the database
		try{
			sqlStatement = connection.prepareCall("{"+dbCommand+"}");
			//adding the input variables to the SP
			sqlStatement.setString(1, transactionId);
			sqlStatement.setString(2, otp);        	
			
			//adding output variables to the SP
			sqlStatement.registerOutParameter(3,Types.VARCHAR);
			sqlStatement.execute();

			ResultSet resultSet = sqlStatement.getResultSet();
			if(resultSet !=null){ 
				while (resultSet.next()) {
					String isExpire = resultSet.getString(1);
					if(isExpire.equals("1")){
						return true;
					}else{
						return false;
					}
				}
			}else{
				return false;
			}
			errmsg = sqlStatement.getString(3);
			
		}catch(SQLException e){
			errmsg="DB Issue";
			logger.error("Issue while adding transaction otp : "+ errmsg,e);			
		}catch(Exception e){
			errmsg="DB Issue";
			logger.error("Issue while adding transaction otp : "+ errmsg,e);
		}
		finally{
			closeConnection();
		}
		return false;
	}
	
	@Override
	public TransferFunds getTransferFunds(String transactionId,String loggedInUser) throws SQLException{
		TransferFunds transferfunds = new TransferFunds();
		if(transactionId == null || loggedInUser == null){
			logger.error("transactionId or loggedinuser can not be null");
			return null;
		}
			
			CallableStatement sqlStatement;
			String errmsg;
			//command to call the SP
			String dbCommand = DBConstants.SP_CALL+ " " + DBConstants.GET_TRANSFERFUNDS  + "(?,?,?)";
			getConnection();
			
			//establish the connection with the database
					try{
						sqlStatement = connection.prepareCall("{" + dbCommand + "}");
						// adding the input variables to the SP
						sqlStatement.setString(1, transactionId);
						sqlStatement.setString(2, loggedInUser);
						// adding output variables to the SP
						sqlStatement.registerOutParameter(3, Types.VARCHAR);
						sqlStatement.execute();
						ResultSet resultSet = sqlStatement.getResultSet();
						if(resultSet !=null){ 
							while (resultSet.next()) {
								transferfunds.setFromAccountId(resultSet.getString(1));
								transferfunds.setToAccountId(resultSet.getString(2));
								transferfunds.setAmount(resultSet.getDouble(3));								
								break;
							} 
						}
						return transferfunds;
					} catch (SQLException e) {
						errmsg = "DB Issue";
						logger.error("Issue while getting transfer funds object  : "
								+ errmsg, e);
					} catch (Exception e) {
						errmsg = "DB Issue";
						logger.error("Issue while getting transfer funds object : "
								+ errmsg, e);
					} finally {
						closeConnection();
					}
					return null;
		}					
	
	@Override
	public String updateTransferFunds(String transactionId,TransferFunds transferFunds, String loggedInUser, String otp)
			throws SQLException {
		if(transactionId == null || transferFunds == null || loggedInUser == null || otp == null)
			return "TransactionId is null";
	
		String fromAccountId = transferFunds.getFromAccountId();
		String toAccountId = transferFunds.getToAccountId();
		Double amount = transferFunds.getAmount();
		
		CallableStatement sqlStatement;
		String errmsg;
		//command to call the SP
		String dbCommand = DBConstants.SP_CALL+ " " + DBConstants.UPDATE_TRANSFERFUNDS  + "(?,?,?,?,?,?,?,?)";
		getConnection();
		
		//establish the connection with the database
				try{
					sqlStatement = connection.prepareCall("{"+dbCommand+"}");
					//adding the input variables to the SP
					sqlStatement.setString(1, transactionId);
					sqlStatement.setString(2, fromAccountId);
					sqlStatement.setString(3, toAccountId);
					sqlStatement.setDouble(4, amount);
					sqlStatement.setString(5, otp);
					sqlStatement.setInt(6, ITransactionManager.SUCCESS);
					sqlStatement.setString(7, loggedInUser);
					
					//adding output variables to the SP
					sqlStatement.registerOutParameter(8,Types.VARCHAR);
					sqlStatement.execute();
					errmsg = sqlStatement.getString(8);
					logger.info("errmsg : " + errmsg);				
					return errmsg;
				} catch(SQLException e){
					errmsg="DB Issue";
					logger.error("Issue while updating transaction : "+ errmsg,e);			
				} catch(Exception e){
					errmsg="DB Issue";
					logger.error("Issue while updating transaction : "+ errmsg,e);
				} finally{
					closeConnection();
				}
				return errmsg;
	}		
	
}
