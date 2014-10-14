package com.asu.alliancebank.factory;

import com.asu.alliancebank.controllers.usermanagement.backingbean.UserBackingBean;
import com.asu.alliancebank.domain.impl.User;

public interface IUserFactory {

	public User createUserInstance(UserBackingBean userBackingBean);
}
