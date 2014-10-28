package com.asu.alliancebank.service.user;

import java.sql.SQLException;
import java.util.List;

import com.asu.alliancebank.controllers.usermanagement.backingbean.ModifyUserBackingBean;
import com.asu.alliancebank.domain.impl.User;

public interface IUserManager {

	public abstract void addUser(User user, String loggedInUser)
			throws SQLException;

	public List<User> listAllUser(String loggedInUser) throws SQLException;
	public User getUserDetails(String loggedInUser) throws SQLException;
	public boolean isLoginIdUnique(String loginId) throws SQLException;
	public void deleteUser(String userId) throws SQLException;
	public List<String> checkDeleteUserIds(String userIds[],String loggedInUser)throws SQLException;
	public void deleteUsers(List<String> userIds)throws SQLException;
	public boolean doModifyUserDetails( String loggedInUser, String modifyUserId) throws SQLException;
	public void modifyUser(ModifyUserBackingBean modifyUserBackingBean, String loggedInUser, String modifyUserid)throws SQLException;
}