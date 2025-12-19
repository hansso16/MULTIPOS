package com.soses.multilines.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;

import com.soses.multilines.api.user.UserRegistrationFormResponse;
import com.soses.multilines.api.user.UserRegistrationRequest;
import com.soses.multilines.api.user.UserRegistrationResponse;
import com.soses.multilines.common.ViewConstants;
import com.soses.multilines.service.admin.UserRegistrationService;

import ch.qos.logback.core.util.StringUtil;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class AdminUserRegistrationController {

	private static final Logger log = LoggerFactory.getLogger(AdminUserController.class);
	
	private final UserRegistrationService userRegService;

	public AdminUserRegistrationController(UserRegistrationService userRegService) {
		super();
		this.userRegService = userRegService;
	}
	
	@GetMapping("/admin/user/register")
	public String showAddUserForm(Model model) {
		
		log.info("ENTER GET:showAddUserForm(userReq,errors,model)");

		UserRegistrationFormResponse response = userRegService.getUserRegistrationData();
		model.addAttribute("res", response);
		model.addAttribute("roleList", response.getRoleList());
		model.addAttribute("userForm", response.getRequest());

		return ViewConstants.USER_REGISTRATION;
	}
	
	@PostMapping("/admin/user/register")
	public String submitAddUserForm(@ModelAttribute UserRegistrationRequest userForm, Errors errors, Model model) {
		
		log.info("ENTER GET:submitAddUserForm(userReq,errors,model)");

		UserRegistrationResponse response = userRegService.registerUser(userForm);
		if (!StringUtil.isNullOrEmpty(response.getErrorMessage())) {
			model.addAttribute("errorMessage", response.getErrorMessage());
		} else {
			model.addAttribute("successMessage", response.getResponseMessage());
		}

		return showAddUserForm(model);
	}
}
