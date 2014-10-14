package com.asu.alliancebank.db.user;

import java.sql.SQLException;

import com.asu.alliancebank.domain.impl.User;

public interface IUserDBManager {
	public String addUser(User user, String loggedInUser )throws SQLException;
}