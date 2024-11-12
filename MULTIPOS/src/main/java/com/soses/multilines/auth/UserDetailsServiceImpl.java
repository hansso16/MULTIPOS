package com.soses.multilines.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.soses.multilines.entity.User;
import com.soses.multilines.repository.UserRepository;

/**
 * The Class UserDetailsServiceImpl.
 *
 * @author hso
 * @since Nov 12, 2024
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	/** The user repository. */
	UserRepository userRepository;
	
	/**
	 * Sets the user repository.
	 *
	 * @param userRepository the new user repository
	 */
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	/**
	 * Load user by username.
	 *
	 * @param username the username
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
		return new UserDetailsImp(user);
	}

}
