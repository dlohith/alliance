package com.asu.alliancebank.security.password.change;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;

public class ChangePasswordFailure extends
ExceptionMappingAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest req,
			HttpServletResponse res, AuthenticationException authEx)
			throws IOException, ServletException {

		System.out.println("came her");
		if (authEx instanceof DisabledException) {
			res.sendRedirect("changepassword");
		} else {
			super.onAuthenticationFailure(req, res, authEx);
		}

	}

}
