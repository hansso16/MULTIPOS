package com.soses.multilines.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.WebApplicationContext;

import com.soses.multilines.api.user.UserSearchRequest;
import com.soses.multilines.common.ErrorConstants;
import com.soses.multilines.common.MessageConstants;
import com.soses.multilines.common.ViewConstants;
import com.soses.multilines.controller.BaseSearchController;
import com.soses.multilines.dto.UserDetailsDTO;
import com.soses.multilines.entity.User;
import com.soses.multilines.service.user.UserSearchService;
import com.soses.multilines.service.user.UserViewDetailsService;

import jakarta.validation.Valid;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class AdminUserController extends BaseSearchController {
	
	private static final Logger log = LoggerFactory.getLogger(AdminUserController.class);

	private final UserViewDetailsService userViewDetailsService;

	private final UserSearchService<User> userSearchService;

	public AdminUserController(UserViewDetailsService userViewDetailsService,
			UserSearchService<User> userSearchService) {
		super();
		this.userViewDetailsService = userViewDetailsService;
		this.userSearchService = userSearchService;
	}

	@GetMapping("/admin/user")
	public String searchUser(@Valid UserSearchRequest userReq, Errors errors, Model model) {
		log.info("ENTER: searchUser(userReq,errors,model)");
		if (errors.hasErrors()) {
			model.addAttribute(ErrorConstants.ERROR, ErrorConstants.ERROR_VALIDATION_FAILED);
			return ViewConstants.USER_LIST;
		}
		
		String username = userReq.getSearch();
		Page<User> userPage = userSearchService.search(userReq);
		if (userPage != null) {
			setPaginationVariables(userPage, model);
			model.addAttribute("search", username);
			if (userPage.hasContent()) {
				model.addAttribute("users", userPage.getContent());
			} else {
				model.addAttribute(MessageConstants.MESSAGE, MessageConstants.MESSAGE_NO_USER_FOUND);
			}
		}		
		return ViewConstants.USER_LIST;
	}
	
	@GetMapping("/admin/user/{userId}")
	public String viewUserDetails(@PathVariable Integer userId, Model model) throws Exception {
		log.info("ENTER: viewUserDetails(userId,model)");
		
		UserDetailsDTO dto = userViewDetailsService.getUserDetailsView(userId);
		
		model.addAttribute("dto", dto);
		model.addAttribute("userForm", dto.getUserDTO());
		model.addAttribute("privilegeMap", dto.getPrivilegeByModule());
		model.addAttribute("roles", dto.getRoles());
		
		return ViewConstants.USER_DETAILS;
	}
	
}
