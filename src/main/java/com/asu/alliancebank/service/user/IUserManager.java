package com.asu.alliancebank.service.user;

import java.sql.SQLException;
import java.util.List;

import com.asu.alliancebank.domain.impl.User;

public interface IUserManager {

	public abstract void addUser(User user, String loggedInUser)
			throws SQLException;

	public List<User> listAllUser(String loggedInUser) throws SQLException;
	public User getUserDetails(String loggedInUser) throws SQLException;
	public boolean isLoginIdUnique(String loginId) throws SQLException;
}