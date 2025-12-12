package com.soses.multilines.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.soses.multilines.common.StringUtil;
import com.soses.multilines.entity.User;
import com.soses.multilines.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }
    
    public Integer getUserId(String userCode) {
    	User user = null;
    	if (!StringUtil.isEmpty(userCode)) {
    		user = findByUsername(userCode).get();
    	}
    	return user == null? 0 : user.getUserId();
    }
}
