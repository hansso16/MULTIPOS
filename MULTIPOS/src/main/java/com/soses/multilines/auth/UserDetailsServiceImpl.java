package com.soses.multilines.auth;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.soses.multilines.entity.User;
import com.soses.multilines.repository.PrivilegeRepository;
import com.soses.multilines.repository.RoleRepository;
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
	
	private PrivilegeRepository privilegeRepository;
	
	private RoleRepository roleRepository;
	
	/**
	 * Sets the user repository.
	 *
	 * @param userRepository the new user repository
	 */
	public UserDetailsServiceImpl(UserRepository userRepository, PrivilegeRepository privilegeRepository,
			RoleRepository roleRepository) {
		super();
		this.userRepository = userRepository;
		this.privilegeRepository = privilegeRepository;
		this.roleRepository = roleRepository;
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
		Optional<User> user = userRepository.findByUsername(username);
		
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
		return new UserDetailsImp(user.get(), roleRepository, privilegeRepository);
	}

}
