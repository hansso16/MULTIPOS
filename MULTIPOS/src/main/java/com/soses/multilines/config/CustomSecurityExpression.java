package com.soses.multilines.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("customSecurity")
public class CustomSecurityExpression {

	private static final Logger log = LoggerFactory.getLogger(CustomSecurityExpression.class);
	
	public boolean hasAnyAuthorityPrefix(String... prefixes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getAuthorities() == null) return false;

        for (GrantedAuthority authority : auth.getAuthorities()) {
        	for (String prefix : prefixes) {
        		if (authority.getAuthority().startsWith(prefix)) {
        			return true;
        		}
        	}
        }
        return false;
    }
	
	public boolean hasAuthorityPrefix(String... prefixes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getAuthorities() == null) return false;

        for (String prefix : prefixes) {
        	boolean prefixMatched = false;
        	for (GrantedAuthority authority : auth.getAuthorities()) {
        		if (authority.getAuthority().startsWith(prefix)) {
        			prefixMatched = true;
        			break;
        		}
        	}
        	
        	if (!prefixMatched) {
        		return false;
        	}
        }
        return true;
    }
}
