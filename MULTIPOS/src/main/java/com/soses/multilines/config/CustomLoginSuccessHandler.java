package com.soses.multilines.config;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		String redirectUrl = "/";
		
		Collection<? extends GrantedAuthority> auth = authentication.getAuthorities();
		
		if (auth.stream().anyMatch(a -> "ROLE_AGENT".equals(a.getAuthority()))) {
			redirectUrl = "/customerinventory";
		}
		
		response.sendRedirect(redirectUrl);
		
	}

}
