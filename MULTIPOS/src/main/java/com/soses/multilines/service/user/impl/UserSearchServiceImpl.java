package com.soses.multilines.service.user.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.soses.multilines.api.BaseSearchRequest;
import com.soses.multilines.api.user.UserSearchRequest;
import com.soses.multilines.entity.User;
import com.soses.multilines.repository.UserRepository;
import com.soses.multilines.service.user.UserSearchService;

import ch.qos.logback.core.util.StringUtil;
import jakarta.transaction.Transactional;

@Service("UserSearchServiceImpl")
@Transactional
public class UserSearchServiceImpl implements UserSearchService<User> {

	private static final Logger log = LoggerFactory.getLogger(UserSearchServiceImpl.class);
	
	private UserRepository userRepo;
	
	public UserSearchServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
	@Override
	public Page<User> search(BaseSearchRequest request) {
		log.info("ENTER searchUser(request)");

		UserSearchRequest userSearchReq = null; 
		String username = null;
		Page<User> userPage = null;
		int pageSize = 5;
		int currentPage = 0;

		if (request != null) {
			userSearchReq = (UserSearchRequest) request;
			username = userSearchReq.getUsername();
			
			if (!StringUtil.isNullOrEmpty(userSearchReq.getSize())) {
				pageSize = Integer.parseInt(userSearchReq.getSize());
			}
			if (!StringUtil.isNullOrEmpty(userSearchReq.getPage())) {
				currentPage = Integer.parseInt(userSearchReq.getPage()) - 1;
			}
			
			Pageable page = PageRequest.of(currentPage, pageSize);
			if (!StringUtil.isNullOrEmpty(username)) {
				userPage = userRepo.findByUsernameContains(username, page);
			} else {
				userPage = userRepo.findAll(page);
			}
		}
		
		return userPage;
	}

}
