package com.soses.multilines.controller.user;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.soses.multilines.api.user.UserRegistrationRequest;
import com.soses.multilines.api.user.UserRegistrationResponse;
import com.soses.multilines.common.ErrorConstants;
import com.soses.multilines.common.GeneralUtil;
import com.soses.multilines.common.MessageConstants;
import com.soses.multilines.common.UrlConstants;
import com.soses.multilines.common.ViewConstants;
import com.soses.multilines.entity.Role;
import com.soses.multilines.entity.User;
import com.soses.multilines.service.RoleService;
import com.soses.multilines.service.admin.UserRegistrationService;

import ch.qos.logback.core.util.StringUtil;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class UserRegistrationController {

	private static final Logger log = LoggerFactory.getLogger(UserRegistrationController.class);
	
	private RoleService roleService;
	
	private UserRegistrationService userRegistrationService;
	
	public UserRegistrationController(RoleService roleService, @Qualifier("UserRegistrationServiceImpl") UserRegistrationService userRegistrationService) {
		super();
		this.roleService = roleService;
		this.userRegistrationService = userRegistrationService;
	}

	@GetMapping("/user/register")
	public String registerUser(Model model, Principal principal) {
		log.info("ENTER GET:registerUser(model)");

		List<Role> roleList = roleService.findAllRoles();
		
		if (!GeneralUtil.isListEmpty(roleList)) {
			model.addAttribute("roles", roleList);
		}
		
		model.addAttribute("user", new User());

		return ViewConstants.USER_REGISTRATION;
	}
	
	@PostMapping("/user/register")
	public RedirectView registerUser(Model model, UserRegistrationRequest request, RedirectAttributes redirectAttrs) {
		
		log.info("ENTER POST(model, request):"+request.toString());
		
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setExposeModelAttributes(false);
		redirectView.setUrl(UrlConstants.USER_REGISTER);
		UserRegistrationResponse res = userRegistrationService.registerUser(request);
		
		if (res != null) {
			if (res.getErrorMessage() != null) {
				log.info(res.getErrorMessage());
				redirectAttrs.addFlashAttribute(ErrorConstants.ERROR_MESSAGE, res.getErrorMessage());
				return redirectView;
			}
			if (!StringUtil.isNullOrEmpty(res.getResponseMessage())) {
				redirectAttrs.addFlashAttribute(MessageConstants.SUCCESS_MESSAGE, res.getResponseMessage());
				return redirectView;
			}
			model.addAttribute("res", res);
		}
		
		return redirectView;
	}
}
