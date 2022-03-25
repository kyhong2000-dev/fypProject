package com.fyp.webapp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		authentication = SecurityContextHolder.getContext().getAuthentication();
		
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

		String redirectURL = request.getContextPath();
		
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			redirectURL = "/login";
		}

		if (userDetails.hasRole("Admin")) {
			redirectURL = "/admin_Home";
		} else if (userDetails.hasRole("Lecturer")) {
			redirectURL = "/lecturer_Home";
		} else if (userDetails.hasRole("Student")) {
			redirectURL = "/student_Home";
		}

		response.sendRedirect(redirectURL);
	}
}
