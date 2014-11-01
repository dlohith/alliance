package com.asu.alliancebank.db.transaction.impl;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.UUID;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.asu.alliancebank.db.DBConstants;
import com.asu.alliancebank.db.transaction.ICreditFundsDBManager;
import com.asu.alliancebank.db.user.impl.UserDBManager;
import com.asu.alliancebank.domain.impl.CreditFunds;
import com.asu.alliancebank.factory.ICreditFundsFactory;
import com.asu.alliancebank.service.transaction.ICreditFundsManager;
import com.asu.alliancebank.service.transaction.ITransactionManager;

public class CreditFundsDBManager implements ICreditFundsDBManager{
	private static final Logger logger = LoggerFactory
			.getLogger(UserDBManager.class);
	
	private Connection connection;
	
	@Autowired
	private ICreditFundsFactory creditFundsFactory;
	
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
	}
