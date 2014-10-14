package com.asu.alliancebank.service.user;

import java.sql.SQLException;

import com.asu.alliancebank.domain.impl.User;

public interface IUserManager {

	public abstract void addUser(User user, String loggedInUser)
			throws SQLException;

}