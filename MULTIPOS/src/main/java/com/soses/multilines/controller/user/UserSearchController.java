package com.soses.multilines.controller.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;

import com.soses.multilines.api.user.UserSearchRequest;
import com.soses.multilines.common.ErrorConstants;
import com.soses.multilines.common.MessageConstants;
import com.soses.multilines.common.ViewConstants;
import com.soses.multilines.controller.BaseSearchController;
import com.soses.multilines.entity.User;
import com.soses.multilines.service.admin.UserSearchService;

import jakarta.validation.Valid;

/**
 * The Class UserController.
 *
 * @author hso
 * @since Nov 8, 2024
 */
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class UserSearchController extends BaseSearchController {

	private UserSearchService<User> userSearchService;
	
	/**
	 * Instantiates a new user search controller.
	 *
	 * @param userSearchService the user search service
	 */
	public UserSearchController(@Qualifier("UserSearchServiceImpl")UserSearchService<User> userSearchService) {
		super();
		this.userSearchService = userSearchService;
	}
	
	/**
	 * Search entity.
	 *
	 * @param userReq the user req
	 * @param errors the errors
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/user")
	public String searchEntity(@Valid UserSearchRequest userReq, Errors errors, Model model) {
		
		if (errors.hasErrors()) {
			model.addAttribute(ErrorConstants.ERROR, ErrorConstants.ERROR_VALIDATION_FAILED);
			return ViewConstants.USER_LIST;
		}
		
		String username = userReq.getUsername();
		Page<User> userPage = userSearchService.search(userReq);
		if (userPage != null) {
			setPaginationVariables(userPage, model);
			model.addAttribute("username", username);
			if (userPage.hasContent()) {
				model.addAttribute("users", userPage.getContent());
			} else {
				model.addAttribute(MessageConstants.MESSAGE, MessageConstants.MESSAGE_NO_USER_FOUND);
			}
		}		
		return ViewConstants.USER_LIST;
	}
}
