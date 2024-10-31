package com.soses.multilines.auth;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.soses.hris.common.GeneralUtil;
import com.soses.multilines.entity.User;

public class UserDetailsImp implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7137660071839033186L;
	private String username;
	private String password;
	private boolean isEnabled;
	private LocalDate terminationDate;
	private List<Role> roles;

	public UserDetailsImp() { }
	
	public UserDetailsImp(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.terminationDate = user.getTerminationDate();
		if(terminationDate != null && terminationDate.isBefore(java.time.LocalDate.now())) {
			this.isEnabled = false;
		} else { 
			this.isEnabled = true;
		}
		this.roles = Arrays.asList(user.getRole());
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> authorities = new ArrayList<>();
    	if (!GeneralUtil.isListEmpty(roles)) {
            for (Role role: roles) {
            	authorities.add(new SimpleGrantedAuthority(role.getFullRoleCode()));
            }
        }
        return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isEnabled;
	}
	
	public LocalDate getTerminationDate() {
		return terminationDate;
	}

}
