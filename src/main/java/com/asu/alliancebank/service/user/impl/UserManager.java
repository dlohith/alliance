package com.asu.alliancebank.service.user.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.asu.alliancebank.db.user.IUserDBManager;
import com.asu.alliancebank.domain.impl.User;
import com.asu.alliancebank.service.user.IUserManager;

@Service
public class UserManager implements IUserManager {

	@Autowired
	@Qualifier("UserDBManagerBean")
	private IUserDBManager dbConnect;
	

	/* (non-Javadoc)
	 * @see com.asu.alliancebank.service.user.impl.IUserManager#addUser(com.asu.alliancebank.domain.impl.User, java.lang.String)
	 */
	@Override
	public void addUser(User user, String loggedInUser) throws SQLException{
		if(user != null){
			dbConnect.addUser(user, loggedInUser);
		}
		
	}
}
