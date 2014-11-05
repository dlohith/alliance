/**
 * 
 */
package com.asu.alliancebank.controllers.transactionmanagement.backingbean;

import com.asu.alliancebank.annotation.NotEmpty;
import com.asu.alliancebank.annotation.NotInvalidString;

/**
 * @author Kedar
 *
 */
public class AuthorizeCustRequestBackingBean {
	
	@NotEmpty(message = "Please provide a user ID.")
	@NotInvalidString(message = "Please check the user ID you have given - either its too big or it has invalid content")
	private String userLoginID;
	
	@NotEmpty(message = "Please provide a employee ID.")
	@NotInvalidString(message = "Please check the employee id you have given - either its too big or it has invalid content")
	private String employeeLoginID;

	public String getUserLoginID() {
		return userLoginID;
	}

	public void setUserLoginID(String userLoginID) {
		this.userLoginID = userLoginID;
	}

	public String getEmployeeLoginID() {
		return employeeLoginID;
	}

	public void setEmployeeLoginID(String employeeLoginID) {
		this.employeeLoginID = employeeLoginID;
	}
	
	
}
