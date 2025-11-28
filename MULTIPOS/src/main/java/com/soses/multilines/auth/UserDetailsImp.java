package com.soses.multilines.auth;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.collection.spi.PersistentSet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.soses.multilines.entity.Privilege;
import com.soses.multilines.entity.Role;
import com.soses.multilines.entity.User;

/**
 * The Class UserDetailsImp.
 *
 * @author hso
 * @since Nov 12, 2024
 */
public class UserDetailsImp implements UserDetails {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7137660071839033186L;
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
	/** The is enabled. */
	private boolean isEnabled;
	
	/** The termination date. */
	private LocalDate terminationDate;
	
	/** The role set. */
	private Set<Role> roleSet;
	
	/** The privilege set. */
	private Set<Privilege> privilegeSet;

	/**
	 * Instantiates a new user details imp.
	 */
	public UserDetailsImp() { }
	
	/**
	 * Instantiates a new user details imp.
	 *
	 * @param user the user
	 */
	public UserDetailsImp(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.terminationDate = user.getTerminationDate();
		this.roleSet = user.getRoleSet();
		if (!roleSet.isEmpty()) {
			for (Role r : roleSet) {
				privilegeSet = r.getPrivilegeSet();
			}
		} else {
			privilegeSet = new PersistentSet<>();
		}
		if(terminationDate != null && terminationDate.isBefore(java.time.LocalDate.now())) {
			this.isEnabled = false;
		} else { 
			this.isEnabled = true;
		}
	}
	
	/**
	 * Gets the authorities.
	 *
	 * @return the authorities
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Set<GrantedAuthority> authorities = new HashSet<>();
		
		if (!roleSet.isEmpty()) {
			for (Role role : roleSet) {
				if ((LocalDate.now().isAfter(role.getStartLocalDate()) || LocalDate.now().isEqual(role.getStartLocalDate()))
						&& (LocalDate.now().isBefore(role.getEndLocalDate()) || LocalDate.now().isEqual(role.getEndLocalDate()))) {
					authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
				}
			}
		}
		
		if (!privilegeSet.isEmpty()) {
			for (Privilege privilege : privilegeSet) {
				if ((LocalDate.now().isAfter(privilege.getStartLocalDate()) || LocalDate.now().isEqual(privilege.getStartLocalDate()))
						&& LocalDate.now().isBefore(privilege.getEndLocalDate()) || LocalDate.now().isEqual(privilege.getEndLocalDate())) {
					authorities.add(new SimpleGrantedAuthority(privilege.getPrivilegeName()));
				}
			}
		}
        return authorities;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/**
	 * Checks if is account non expired.
	 *
	 * @return true, if is account non expired
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Checks if is account non locked.
	 *
	 * @return true, if is account non locked
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Checks if is credentials non expired.
	 *
	 * @return true, if is credentials non expired
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Checks if is enabled.
	 *
	 * @return true, if is enabled
	 */
	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
	
	/**
	 * Gets the termination date.
	 *
	 * @return the termination date
	 */
	public LocalDate getTerminationDate() {
		return terminationDate;
	}

}
