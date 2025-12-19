package com.soses.multilines.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.soses.multilines.entity.User;

/**
 * The Interface UserRepository.
 *
 * @author hso
 * @since Nov 8, 2024
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the optional
	 */
	Optional<User> findByUsername(String username);
	
	/**
	 * Find by username contains.
	 *
	 * @param username the username
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<User> findByUsernameContains(String username, Pageable pageable);
	

	@Query("""
	        SELECT u FROM User u
	        WHERE 
				(
					:username IS NULL
		            OR :username = ''
		            OR u.username LIKE CONCAT('%', :username, '%')
				) AND NOT EXISTS
				(
					SELECT r FROM u.roleSet r WHERE r.roleName = 'ROLE_ADMIN'
				)
	    """)
	Page<User> searchByUsername(String username, Pageable pageable);
	
//	/**
//	 * Terminate user.
//	 *
//	 * @param terminationDate the termination date
//	 * @param username the username
//	 */
//	@Modifying
//	@Query("UPDATE user u SET u.terminationDate = :terminationDate WHERE u.username = :username")
//	void terminateUser(@Param("terminationDate") LocalDate terminationDate, @Param("username") String username);
//
//	/**
//	 * Update password.
//	 *
//	 * @param password the password
//	 * @param username the username
//	 */
//	@Modifying
//	@Query("UPDATE user u SET u.password = :password WHERE u.username = :username")
//	void updatePassword(@Param("password") String password, @Param("username") String username);
//	
//	/**
//	 * Update acess.
//	 *
//	 * @param role the role
//	 * @param username the username
//	 */
//	@Modifying
//	@Query("UPDATE user u SET u.role = :role WHERE u.username = :username")
//	void updateAcess(@Param("role") Role role, @Param("username") String username);		
}
