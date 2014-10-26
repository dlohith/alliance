package com.asu.alliancebank.db.user.impl;

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
import com.asu.alliancebank.db.user.IUserDBManager;
import com.asu.alliancebank.domain.impl.User;
import com.asu.alliancebank.factory.IUserFactory;
import com.asu.alliancebank.service.role.IRoleManager;
import com.asu.alliancebank.service.userservice.AllianceBankGrantedAuthority;


public class UserDBManager implements IUserDBManager {

	private static final Logger logger = LoggerFactory
			.getLogger(UserDBManager.class);
	
	private Connection connection;
	
	@Autowired
	private IUserFactory userFactory;
	
	@Autowired
	private IRoleManager roleManager;
	
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
	public String addUser(User user, String loggedInUser )throws SQLException
	{
		if(user==null){
			return "User object is null" ;
		}
		
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String loginId = user.getLoginID();
		String emailId = user.getEmailId();
		String phoneNo = user.getPhoneNo();
		String password = user.getPassword();
		String roleId ="";;
		List<AllianceBankGrantedAuthority> authorities = user.getAuthorities();
		
		for(AllianceBankGrantedAuthority authority : authorities){
			if(roleId.isEmpty()){
				roleId = authority.getAuthority();
			}else{
				roleId = roleId + ","+ authority.getAuthority();
			}
		}
		String userId = generateUniqueID();
		String dbCommand;
		String errmsg;
		CallableStatement sqlStatement;
		
		//command to call the SP
		dbCommand = DBConstants.SP_CALL+ " " + DBConstants.ADD_USER  + "(?,?,?,?,?,?,?,?,?,?)";
		
		//get the connection
		getConnection();
		//establish the connection with the database
		try{
			sqlStatement = connection.prepareCall("{"+dbCommand+"}");
			//adding the input variables to the SP
			sqlStatement.setString(1, userId);
			sqlStatement.setString(2, firstName);        	
			sqlStatement.setString(3, lastName);
			sqlStatement.setString(4, loginId);
			sqlStatement.setString(5, password);
			sqlStatement.setString(6, emailId);
			sqlStatement.setString(7, phoneNo);
			sqlStatement.setString(8, roleId);
			sqlStatement.setString(9, loggedInUser);
			
			//adding output variables to the SP
			sqlStatement.registerOutParameter(10,Types.VARCHAR);
			sqlStatement.execute();

			errmsg = sqlStatement.getString(10);
			
			return errmsg;
		}catch(SQLException e){
			errmsg="DB Issue";
			logger.error("Issue while adding user : "+ errmsg,e);			
		}catch(Exception e){
			errmsg="DB Issue";
			logger.error("Issue while adding user : "+ errmsg,e);
		}
		finally{
			closeConnection();
		}
		return errmsg;
		
	}
	
	/**
	 * Generate an unique identifier for the database field
	 */
	public String generateUniqueID()
	{
		return UUID.randomUUID().toString();
	}
	
	@Override
	public List<User> listAllUsers( String loggedInUser )throws SQLException{
		
		List<User> users = new ArrayList<User>();
		String dbCommand;
		String errmsg;
		CallableStatement sqlStatement;
		
		//command to call the SP
		dbCommand = DBConstants.SP_CALL+ " " + DBConstants.LIST_ALL_USER  + "(?,?)";
		//get the connection
		getConnection();
		//establish the connection with the database
		try{
			sqlStatement = connection.prepareCall("{"+dbCommand+"}");
			//adding the input variables to the SP
			sqlStatement.setString(1, loggedInUser);
			
			
			//adding output variables to the SP
			sqlStatement.registerOutParameter(2,Types.VARCHAR);
			sqlStatement.execute();

			ResultSet resultSet = sqlStatement.getResultSet();
			if(resultSet !=null){ 
				while (resultSet.next()) {
					User user = userFactory.createEmptyUserObject();
					user.setUserId(resultSet.getString(1));
					user.setFirstName(resultSet.getString(2));
					user.setLastName(resultSet.getString(3));
					user.setLoginID(resultSet.getString(4));
					user.setEmailId(resultSet.getString(5));
					user.setPhoneNo(resultSet.getString(6));
					String roleIds = resultSet.getString(7);
					
					user.setAuthorities(user.getAuthorities(roleManager.getRoleList(roleIds)));
					
					users.add(user);
				} 
			}
			
			errmsg = sqlStatement.getString(2);
			return users;
		}catch(SQLException e){
			errmsg="DB Issue";
			logger.error("Issue while adding user : "+ errmsg,e);			
		}catch(Exception e){
			errmsg="DB Issue";
			logger.error("Issue while adding user : "+ errmsg,e);
		}
		finally{
			closeConnection();
		}
		return users;
	}
	
	
	@Override
	public boolean isLoginIdUnique( String loginId)throws SQLException{
		
		if(loginId.isEmpty())
			return false;
		
		String dbCommand;
		String errmsg;
		CallableStatement sqlStatement;
		
		//command to call the SP
		dbCommand = DBConstants.SP_CALL+ " " + DBConstants.IS_LOGIN_UNIQUE  + "(?,?)";
		//get the connection
		getConnection();
		//establish the connection with the database
		try{
			sqlStatement = connection.prepareCall("{"+dbCommand+"}");
			//adding the input variables to the SP
			sqlStatement.setString(1, loginId);
			
			
			//adding output variables to the SP
			sqlStatement.registerOutParameter(2,Types.VARCHAR);
			sqlStatement.execute();

			ResultSet resultSet = sqlStatement.getResultSet();
			if(resultSet !=null){ 
				while (resultSet.next()) {
					
					return false;
				} 
			}
			return true;

		}catch(SQLException e){
			errmsg="DB Issue";
			logger.error("Issue while adding user : "+ errmsg,e);			
		}catch(Exception e){
			errmsg="DB Issue";
			logger.error("Issue while adding user : "+ errmsg,e);
		}
		finally{
			closeConnection();
		}
		return false;
	}
	
	@Override
	public User getUserDetails( String loggedInUser )throws SQLException{
		
		User user = null;
		String dbCommand;
		String errmsg;
		CallableStatement sqlStatement;
		
		//command to call the SP
		dbCommand = DBConstants.SP_CALL+ " " + DBConstants.GET_USER  + "(?,?)";
		//get the connection
		getConnection();
		//establish the connection with the database
		try{
			sqlStatement = connection.prepareCall("{"+dbCommand+"}");
			//adding the input variables to the SP
			sqlStatement.setString(1, loggedInUser);
			
			
			//adding output variables to the SP
			sqlStatement.registerOutParameter(2,Types.VARCHAR);
			sqlStatement.execute();

			ResultSet resultSet = sqlStatement.getResultSet();
			if(resultSet !=null){ 
				while (resultSet.next()) {
					user = userFactory.createEmptyUserObject();
					user.setUserId(resultSet.getString(1));
					user.setFirstName(resultSet.getString(2));
					user.setLastName(resultSet.getString(3));
					user.setLoginID(resultSet.getString(4));
					user.setPassword(resultSet.getString(5));
					user.setEmailId(resultSet.getString(6));
					user.setPhoneNo(resultSet.getString(7));
					String roleIds = resultSet.getString(8);
					
					user.setAuthorities(user.getAuthorities(roleManager.getRoleList(roleIds)));
					
					break;
				} 
			}
			
			errmsg = sqlStatement.getString(2);
			return user;
		}catch(SQLException e){
			errmsg="DB Issue";
			logger.error("Issue while adding user : "+ errmsg,e);			
		}catch(Exception e){
			errmsg="DB Issue";
			logger.error("Issue while adding user : "+ errmsg,e);
		}
		finally{
			closeConnection();
		}
		return user;
	}
}
