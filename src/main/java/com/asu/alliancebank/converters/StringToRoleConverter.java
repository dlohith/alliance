package com.asu.alliancebank.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.asu.alliancebank.domain.impl.Role;
import com.asu.alliancebank.service.role.IRoleManager;

/**
 * Converts for the spring conversion of String to role
 * 
 * @author : Lohith Dwaraka
 *
 */
public class StringToRoleConverter implements Converter<String, Role>{

	@Autowired
	private IRoleManager roleManager;
	
	/**
	 * Converts String to Role object
	 */
	@Override
	public Role convert(String arg0) {
		System.out.println("data");
		return roleManager.getRole(arg0);
	}
}
