package com.asu.alliancebank.service.user.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.db.user.IUserDBManager;
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
			
			User user = userFactory.createUserInstance("Jack", "Wilshere", "jackwill", "1@m@rsen@l", "jackwill@gmail.com", "44444444444", authorities);
			
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
	public List<User> listAllUser(String loggedInUser) throws SQLException{
		List<User> users = null;
		if(loggedInUser != null){
			users = dbConnect.listAllUsers(loggedInUser);
		}
		return users;
	}
	
	@Override
	public User getUserDetails(String loggedInUser) throws SQLException{
		User user = null;
		if(loggedInUser != null){
			user = dbConnect.getUserDetails(loggedInUser);
		}
		return user;
	}
}
