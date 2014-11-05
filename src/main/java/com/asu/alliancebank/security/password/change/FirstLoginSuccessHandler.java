package com.asu.alliancebank.security.password.change;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.asu.alliancebank.service.user.IUserManager;

public class FirstLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	@Autowired
	private IUserManager userManager;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authResult) throws IOException,
			ServletException {
		
		
	    if (authResult != null) {
	    	try {
				if(userManager.isFirstTimeLogin(authResult.getName())){
					request.getSession().invalidate();
					response.sendRedirect("changepassword");
				}else{
					super.onAuthenticationSuccess(request, response, authResult);
				}
			} catch (SQLException e) {
			}
	    }
		
	}
	

}
