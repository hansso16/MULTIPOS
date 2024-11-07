package com.soses.multilines.auth;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.soses.multilines.entity.Privilege;
import com.soses.multilines.entity.Role;
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
	private Set<Role> roleSet;
	private Set<Privilege> privilegeSet;

	public UserDetailsImp() { }
	
	public UserDetailsImp(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.terminationDate = user.getTerminationDate();
		this.roleSet = user.getRoleSet();
		this.privilegeSet = user.getPrivileges();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Set<GrantedAuthority> authorities = new HashSet<>();
		
		if (!roleSet.isEmpty()) {
			for (Role role : roleSet) {
				authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			}
		}
		
		if (!privilegeSet.isEmpty()) {
			for (Privilege privilege : privilegeSet) {
				authorities.add(new SimpleGrantedAuthority(privilege.getPrivilegeName()));
			}
		}
		
//		List<GrantedAuthority> authorities = new ArrayList<>();
//    	if (roleSet != null && !roleSet.isEmpty()) {
//            for (Role role: roleSet) {
//            	authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//            }
//        }
		
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
		if(terminationDate != null && terminationDate.isBefore(java.time.LocalDate.now())) {
			this.isEnabled = false;
		} else { 
			this.isEnabled = true;
		}
		return isEnabled;
	}
	
	public LocalDate getTerminationDate() {
		return terminationDate;
	}

}
