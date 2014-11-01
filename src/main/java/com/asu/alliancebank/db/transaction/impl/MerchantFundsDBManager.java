/**
 * 
 */
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

import com.asu.alliancebank.controllers.transactionmanagement.backingbean.MerchantDebitFundsBackingBean;
import com.asu.alliancebank.db.DBConstants;
import com.asu.alliancebank.db.transaction.IMerchantFundsDBManager;
import com.asu.alliancebank.service.transaction.ITransactionManager;

/**
 * @author Kedar
 *
 */
public class MerchantFundsDBManager implements IMerchantFundsDBManager {

	private Connection connection;	
	
	@Autowired
	private DataSource dataSource;
	
	private static final Logger logger = LoggerFactory
			.getLogger(TransactionDBManager.class);
	
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
	public String addMerchantRequest(MerchantDebitFundsBackingBean merchantBackingBean, String loggedInUser)
			throws SQLException {
		if (merchantBackingBean == null)
			return "Backingbean object is null";

		String requestID = generateUniqueID();
		CallableStatement sqlStatement;
		String errmsg;
		// command to call the SP
		String dbCommand = DBConstants.SP_CALL + " "
				+ DBConstants.ADD_MERCHANTREQUEST + "(?,?,?,?,?,?)";
		getConnection();

		// establish the connection with the database
		try {
			Long amount = Long.parseLong(merchantBackingBean.getAmount());
			sqlStatement = connection.prepareCall("{" + dbCommand + "}");
			// adding the input variables to the SP
			sqlStatement.setString(1, requestID);
			sqlStatement.setString(2, loggedInUser);
			sqlStatement.setString(3, merchantBackingBean.getUserLoginID());
			sqlStatement.setLong(4, amount);
			sqlStatement.setInt(5, ITransactionManager.PENDING);
			// adding output variables to the SP
			sqlStatement.registerOutParameter(6, Types.VARCHAR);
			sqlStatement.execute();
			errmsg = sqlStatement.getString(6);
			return errmsg;
		} catch (SQLException e) {
			errmsg = "DB Issue";
			logger.error("Issue while adding initial merchant request : "
					+ errmsg, e);
		} catch (Exception e) {
			errmsg = "DB Issue";
			logger.error("Issue while adding initial merchant request : "
					+ errmsg, e);
		} finally {
			closeConnection();
		}
		return errmsg;
	}

}
