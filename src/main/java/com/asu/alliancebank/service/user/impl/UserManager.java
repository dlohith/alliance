package com.asu.alliancebank.service.user.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import com.asu.alliancebank.controllers.usermanagement.backingbean.ModifyUserBackingBean;
import com.asu.alliancebank.db.user.IUserDBManager;
import com.asu.alliancebank.domain.impl.Role;
import com.asu.alliancebank.domain.impl.User;
import com.asu.alliancebank.factory.IUserFactory;
import com.asu.alliancebank.service.role.IRoleManager;
import com.asu.alliancebank.service.user.IUserManager;
import com.asu.alliancebank.service.userservice.AllianceBankGrantedAuthority;

@Service
public class UserManager implements IUserManager {

	@Autowired
	@Qualifier("UserDBManagerBean")
	private IUserDBManager dbConnect;
	
	@Autowired
	private IUserFactory userFactory;
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserManager.class);
	
	@PostConstruct
	public void addDefaultUser(){
		List<User> users = null;
		try {
			users = listAllUser("iamcheckingifanyuser");
		} catch (SQLException e1) {
			logger.error("Issue while getting users ",e1);
		}
		if(users == null || users.size() == 0){
			logger.info("No user in the system. would create an Admin");
			List<AllianceBankGrantedAuthority> authorities = new ArrayList<AllianceBankGrantedAuthority>();
			AllianceBankGrantedAuthority adminAuthority = new AllianceBankGrantedAuthority(IRoleManager.ROLE_SYSTEM_ADMIN);
			authorities.add(adminAuthority);
			
			User user = userFactory.createUserInstance("Jack", "Wilshere", "jackwill", "testing", "jackwill@gmail.com", "44444444444", authorities);
			
			try {
				addUser(user, "GOD");
			} catch (SQLException e) {
				logger.error("Issue while creating default user ",e);
			}
		}
		
		
	}


	@Override
	public void addUser(User user, String loggedInUser) throws SQLException{
		if(user != null){
			dbConnect.addUser(user, loggedInUser);
		}
		
	}
	
	@Override
	public void deleteUser(String userId) throws SQLException{
		if(userId != null ){
			dbConnect.deleteUser(userId);
		}
	}
	
	@Override
	public List<User> listAllUser(String loggedInUser) throws SQLException{
		List<User> users = null;
		if(loggedInUser != null){
			users = dbConnect.listAllUsers(loggedInUser);
		}
		return users;
	}
	
	/**
	 * Checks and return appropriate user ids to delete.
	 * To avoid unwanted user delete
	 */
	@Override
	public List<String> checkDeleteUserIds(String userIds[], String loggedInUser)throws SQLException{
		List<User> users = dbConnect.listAllUsers(loggedInUser);
		Map<String, User> userMap = convertListOfUserToMap(users);
		List<String> userIdsMarkedForDelete = new ArrayList<String>();
		for(int i=0 ;i<userIds.length;i++){
			if(userMap.containsKey(userIds[i])){
				userIdsMarkedForDelete.add(userIds[i]);
			}
		}
		
		return userIdsMarkedForDelete;
	}
	
	@Override
	public boolean doModifyUserDetails(String loggedInUser, String modifyUserId) throws SQLException{
		List<User> users = dbConnect.listAllUsers(loggedInUser);
		for(User user : users){
			if(user.getLoginID().equals(modifyUserId))
				return true;
		}
		return false;
	}
	
	private Map<String, User> convertListOfUserToMap(List<User> users){
		Map<String, User> userMap = new HashMap<String, User>();
		
		for(User user : users){
			userMap.put(user.getUserId(),user);
		}
		
		return userMap;
	}
	
	@Override
	public void deleteUsers(List<String> userIds)throws SQLException{
		for(String userId : userIds){
			deleteUser(userId);
		}
	}
	
	@Override
	public boolean isLoginIdUnique(String loginId) throws SQLException{
		return dbConnect.isLoginIdUnique(loginId);
	}
	
	@Override
	public User getUserDetails(String loggedInUser) throws SQLException{
		User user = null;
		if(loggedInUser != null){
			user = dbConnect.getUserDetails(loggedInUser);
		}
		return user;
	}
	
	@Override
	public void modifyUser(ModifyUserBackingBean modifyUserBackingBean, String loggedInUser, String modifyUserid)throws SQLException{
		String firstName = modifyUserBackingBean.getFirstName();
		String lastName = modifyUserBackingBean.getLastName();
		String loginId = modifyUserid;
		String emailId = modifyUserBackingBean.getEmailId();
		String phoneNo = modifyUserBackingBean.getPhoneNo();
		List<Role> roles =  modifyUserBackingBean.getRoleList();
		
		dbConnect.modifyUser(firstName, lastName, loginId, emailId, phoneNo, roles, loggedInUser);
		
	}
}
