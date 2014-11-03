package com.asu.alliancebank.db.user;

import java.sql.SQLException;
import java.util.List;

import com.asu.alliancebank.domain.impl.Role;
import com.asu.alliancebank.domain.impl.User;

public interface IUserDBManager {
	public String addUser(User user, String loggedInUser )throws SQLException;
	public List<User> listAllUsers( String loggedInUser )throws SQLException;
	public User getUserDetails( String loggedInUser )throws SQLException;
	public boolean isLoginIdUnique( String loginId)throws SQLException;
	public String deleteUser(String userId )throws SQLException;
	public String modifyUser(String firstName,String lastName ,String modifyLoginId,String emailId
			,String phoneNo, List<Role> roles, String loggedInUser )throws SQLException;
	public String updateFailedLoginAttempts(String userId)throws SQLException;
	public String reseteFailedLoginAttempts(String userId)throws SQLException;
	public String unlockUser(String userId)throws SQLException;
}