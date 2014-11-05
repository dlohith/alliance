/**
 * 
 */
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
import com.asu.alliancebank.db.transaction.IAuthorizePaymentsDBManager;
import com.asu.alliancebank.domain.impl.MerchantRequest;
import com.asu.alliancebank.service.transaction.IDebitFundsManager;
import com.asu.alliancebank.service.transaction.ITransactionManager;

/**
 * @author Kedar
 *
 */
public class AuthorizePaymentDBManager implements IAuthorizePaymentsDBManager {

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
	public String approvePayment(MerchantRequest merchantRequest, String loggedInUser)
			throws SQLException {
		
		if (merchantRequest == null)
			return "MerchantRequest object is null";

		String transactionID = generateUniqueID();
		CallableStatement sqlStatement;
		String errmsg;
		// command to call the SP
		String dbCommand = DBConstants.SP_CALL + " "
				+ DBConstants.AUTHORIZE_PAYMENTS + "(?,?,?,?,?,?,?,?)";
		getConnection();

		// establish the connection with the database
		try {
			Long amount = Long.parseLong(merchantRequest.getAmount());
			sqlStatement = connection.prepareCall("{" + dbCommand + "}");
			// adding the input variables to the SP
			sqlStatement.setString(1, transactionID);
			sqlStatement.setString(2, loggedInUser);
			sqlStatement.setString(3, merchantRequest.getUserLoginID());
			sqlStatement.setString(4, IDebitFundsManager.DEBIT);
			sqlStatement.setLong(5, amount);
			sqlStatement.setInt(6, ITransactionManager.SUCCESS);
			logger.info("Request id is - " + merchantRequest.getRequestID());
			sqlStatement.setString(7, merchantRequest.getRequestID());
			// adding output variables to the SP
			sqlStatement.registerOutParameter(8, Types.VARCHAR);
			sqlStatement.execute();
			errmsg = sqlStatement.getString(8);
			return errmsg;
		} catch (SQLException e) {
			errmsg = "DB Issue";
			logger.error("Issue while executing merchant debit  : "
					+ errmsg, e);
		} catch (Exception e) {
			errmsg = "DB Issue";
			logger.error("Issue while executing merchant debit  : "
					+ errmsg, e);
		} finally {
			closeConnection();
		}
		return errmsg;
		
	}

	@Override
	public MerchantRequest getMerchantRequest(String requestID)
			throws SQLException {
		if(requestID == null) {
			logger.error("Request ID can not be null");
			return null;
		}
		MerchantRequest merchantRequest = new MerchantRequest();
		CallableStatement sqlStatement;
		String errmsg;
		// command to call the SP
		String dbCommand = DBConstants.SP_CALL + " "
				+ DBConstants.GET_MERCHANT_REQUEST + "(?,?)";
		getConnection();

		// establish the connection with the database
		try {
			sqlStatement = connection.prepareCall("{" + dbCommand + "}");
			// adding the input variables to the SP
			sqlStatement.setString(1, requestID);
			// adding output variables to the SP
			sqlStatement.registerOutParameter(2, Types.VARCHAR);
			sqlStatement.execute();
			ResultSet resultSet = sqlStatement.getResultSet();
			if(resultSet !=null){ 
				while (resultSet.next()) {
					merchantRequest.setRequestID(requestID);
					merchantRequest.setMerchantID(resultSet.getString(1));
					merchantRequest.setUserLoginID(resultSet.getString(2));
					merchantRequest.setAmount(resultSet.getString(3));
					merchantRequest.setStatus(resultSet.getInt(4));
					break;
				} 
			}
			return merchantRequest;
		} catch (SQLException e) {
			errmsg = "DB Issue";
			logger.error("Issue while getting merchant request  : "
					+ errmsg, e);
		} catch (Exception e) {
			errmsg = "DB Issue";
			logger.error("Issue while getting merchant request  : "
					+ errmsg, e);
		} finally {
			closeConnection();
		}
		return null;
	}

	@Override
	public String rejectPayment(MerchantRequest merchantRequest,String loggedInUser) throws SQLException {
		if (merchantRequest == null)
			return "MerchantRequest object is null";

		CallableStatement sqlStatement;
		String errmsg;
		// command to call the SP
		String dbCommand = DBConstants.SP_CALL + " "
				+ DBConstants.REJECT_MERCHANT_REQUEST + "(?,?)";
		getConnection();

		// establish the connection with the database
		try {
			sqlStatement = connection.prepareCall("{" + dbCommand + "}");
			// adding the input variables to the SP
			sqlStatement.setString(1, merchantRequest.getRequestID());
			sqlStatement.registerOutParameter(2, Types.VARCHAR);
			sqlStatement.execute();
			errmsg = sqlStatement.getString(2);
			return errmsg;
		} catch (SQLException e) {
			errmsg = "DB Issue";
			logger.error("Issue while executing merchant debit reject : "
					+ errmsg, e);
		} catch (Exception e) {
			errmsg = "DB Issue";
			logger.error("Issue while executing merchant debit reject : "
					+ errmsg, e);
		} finally {
			closeConnection();
		}
		return errmsg;
	}

	@Override
	public List<MerchantRequest> getPendingMerchantRequests(String loggedInUser)
			throws SQLException {
		List<MerchantRequest> pendingRequests = new ArrayList<MerchantRequest>();
		
		CallableStatement sqlStatement;
		String errmsg;
		// command to call the SP
		String dbCommand = DBConstants.SP_CALL + " "
				+ DBConstants.GET_PENDING_MERCHANT_REQUESTS + "(?,?)";
		getConnection();

		// establish the connection with the database
		try {
			sqlStatement = connection.prepareCall("{" + dbCommand + "}");
			sqlStatement.setString(1, loggedInUser);
			// adding output variables to the SP
			sqlStatement.registerOutParameter(2, Types.VARCHAR);
			sqlStatement.execute();
			ResultSet resultSet = sqlStatement.getResultSet();
			if(resultSet !=null){ 
				while (resultSet.next()) {
					MerchantRequest merchantRequest = new MerchantRequest();
					merchantRequest.setRequestID(resultSet.getString(1));
					merchantRequest.setMerchantID(resultSet.getString(2));
					merchantRequest.setUserLoginID(resultSet.getString(3));
					merchantRequest.setAmount(resultSet.getString(4));
					merchantRequest.setStatus(resultSet.getInt(5));
					pendingRequests.add(merchantRequest);
				} 
			}
			return pendingRequests;
		} catch (SQLException e) {
			errmsg = "DB Issue";
			logger.error("Issue while getting merchant request  : "
					+ errmsg, e);
		} catch (Exception e) {
			errmsg = "DB Issue";
			logger.error("Issue while getting merchant request  : "
					+ errmsg, e);
		} finally {
			closeConnection();
		}
		return null;
	}

	@Override
	public String addCustomerAuthRequest(String loggedInUser)
			throws SQLException {

		String requestID = generateUniqueID();
		CallableStatement sqlStatement;
		String errmsg;
		// command to call the SP
		String dbCommand = DBConstants.SP_CALL + " "
				+ DBConstants.ADD_CUST_AUTH_REQUEST + "(?,?,?,?)";
		getConnection();

		// establish the connection with the database
		try {
			sqlStatement = connection.prepareCall("{" + dbCommand + "}");
			// adding the input variables to the SP
			sqlStatement.setString(1, requestID);
			sqlStatement.setString(2, loggedInUser);
			sqlStatement.setString(3,loggedInUser);
			sqlStatement.registerOutParameter(4, Types.VARCHAR);
			sqlStatement.execute();
			errmsg = sqlStatement.getString(4);
			return errmsg;
		} catch (SQLException e) {
			errmsg = "DB Issue";
			logger.error("Issue while adding customer request  : "
					+ errmsg, e);
		} catch (Exception e) {
			errmsg = "DB Issue";
			logger.error("Issue while adding customer request  : "
					+ errmsg, e);
		} finally {
			closeConnection();
		}
		return errmsg;
	}

	@Override
	public List<String> getCustomerListForDisplay(String loggedInUser)
			throws SQLException {
		List<String> userList = new ArrayList<String>();
		
		CallableStatement sqlStatement;
		String errmsg;
		// command to call the SP
		String dbCommand = DBConstants.SP_CALL + " "
				+ DBConstants.GET_CUST_AUTH_REQUEST + "(?)";
		getConnection();

		// establish the connection with the database
		try {
			sqlStatement = connection.prepareCall("{" + dbCommand + "}");
			// adding output variables to the SP
			sqlStatement.registerOutParameter(1, Types.VARCHAR);
			sqlStatement.execute();
			ResultSet resultSet = sqlStatement.getResultSet();
			if(resultSet !=null){ 
				while (resultSet.next()) {
					String userLoginID = resultSet.getString(2);
					userList.add(userLoginID);
				} 
			}
			return userList;
		} catch (SQLException e) {
			errmsg = "DB Issue";
			logger.error("Issue while getting user list  : "
					+ errmsg, e);
		} catch (Exception e) {
			errmsg = "DB Issue";
			logger.error("Issue while getting user list  : "
					+ errmsg, e);
		} finally {
			closeConnection();
		}
		return null;
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
	public String authorizeCustAuthRequest(String userLoginID,
			String employeeLoginID, String loggedInUser) throws SQLException {
		String requestID = generateUniqueID();
		CallableStatement sqlStatement;
		String errmsg;
		// command to call the SP
		String dbCommand = DBConstants.SP_CALL + " "
				+ DBConstants.AUTHORIZE_CUST_REQUEST + "(?,?,?,?,?,?)";
		getConnection();

		// establish the connection with the database
		try {
			sqlStatement = connection.prepareCall("{" + dbCommand + "}");
			// adding the input variables to the SP
			sqlStatement.setString(1, requestID);
			sqlStatement.setString(2, userLoginID);
			sqlStatement.setString(3, getStatusString(ITransactionManager.SUCCESS));
			sqlStatement.setString(4, employeeLoginID);
			sqlStatement.setString(5, loggedInUser);
			sqlStatement.registerOutParameter(6, Types.VARCHAR);
			sqlStatement.execute();
			errmsg = sqlStatement.getString(6);
			return errmsg;
		} catch (SQLException e) {
			errmsg = "DB Issue";
			logger.error("Issue while authorizing customer request  : "
					+ errmsg, e);
		} catch (Exception e) {
			errmsg = "DB Issue";
			logger.error("Issue while authorizing customer request  : "
					+ errmsg, e);
		} finally {
			closeConnection();
		}
		return null;
	}

	@Override
	public List<MerchantRequest> getPendingMerchantRequestsEmployee(
			String loggedInUser) throws SQLException {
List<MerchantRequest> pendingRequests = new ArrayList<MerchantRequest>();
		
		CallableStatement sqlStatement;
		String errmsg;
		// command to call the SP
		String dbCommand = DBConstants.SP_CALL + " "
				+ DBConstants.GET_PENDING_MERCHANT_REQUESTS_EMPL + "(?,?)";
		getConnection();

		// establish the connection with the database
		try {
			sqlStatement = connection.prepareCall("{" + dbCommand + "}");
			sqlStatement.setString(1, loggedInUser);
			// adding output variables to the SP
			sqlStatement.registerOutParameter(2, Types.VARCHAR);
			sqlStatement.execute();
			ResultSet resultSet = sqlStatement.getResultSet();
			if(resultSet !=null){ 
				while (resultSet.next()) {
					MerchantRequest merchantRequest = new MerchantRequest();
					merchantRequest.setRequestID(resultSet.getString(1));
					merchantRequest.setMerchantID(resultSet.getString(2));
					merchantRequest.setUserLoginID(resultSet.getString(3));
					merchantRequest.setAmount(resultSet.getString(4));
					merchantRequest.setStatus(resultSet.getInt(5));
					pendingRequests.add(merchantRequest);
				} 
			}
			return pendingRequests;
		} catch (SQLException e) {
			errmsg = "DB Issue";
			logger.error("Issue while getting merchant request  : "
					+ errmsg, e);
		} catch (Exception e) {
			errmsg = "DB Issue";
			logger.error("Issue while getting merchant request  : "
					+ errmsg, e);
		} finally {
			closeConnection();
		}
		return null;
	}

}
