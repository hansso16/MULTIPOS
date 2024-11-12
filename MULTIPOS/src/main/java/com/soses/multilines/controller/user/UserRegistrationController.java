package com.soses.multilines.controller.user;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.soses.multilines.api.user.UserRegistrationRequest;
import com.soses.multilines.common.GeneralUtil;
import com.soses.multilines.common.ViewConstants;
import com.soses.multilines.entity.Role;
import com.soses.multilines.entity.User;
import com.soses.multilines.service.RoleService;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class UserRegistrationController {

	private static final Logger log = LoggerFactory.getLogger(UserRegistrationController.class);
	
	private RoleService roleService;
	
	public UserRegistrationController(RoleService roleService) {
		super();
		this.roleService = roleService;
	}

	@GetMapping("/user/register")
	public String addEmployee(Model model, Principal principal) {
		log.info("ENTER GET:addEmployee(model)");

		List<Role> roleList = roleService.findAllRoles();
		
		if (!GeneralUtil.isListEmpty(roleList)) {
			model.addAttribute("roles", roleList);
		}
		
		model.addAttribute("user", new User());

		return ViewConstants.USER_REGISTRATION;
	}
	
	@PostMapping("/user/register")
	public RedirectView addEmployee(Model model, UserRegistrationRequest request, RedirectAttributes redirectAttrs) {
//		log.info("ENTER POST(model, request):"+request.toString());
//		
//		UserRegistrationResponse res = userRegistrationService.registerUser(request);
//		RedirectView redirectView = new RedirectView();
//		redirectView.setContextRelative(true);
//		redirectView.setExposeModelAttributes(false);
//		
//		if (res != null) {
//			if (res.getError() != null) {
//				String redirectUrl = "/user/add/"+res.getEmployeeId();
//				redirectView.setUrl(redirectUrl);
//				redirectAttrs.addFlashAttribute(GlobalConstants.ERROR_MESSAGE, res.getError().getMessage());
//				return redirectView;
//			}
//			if (!StringUtil.isEmpty(res.getResponseMessage())) {
//				String redirectUrl = "/user/" + res.getUsername();
//				redirectView.setUrl(redirectUrl);
//				redirectAttrs.addFlashAttribute(GlobalConstants.SUCCESS_MESSAGE, res.getResponseMessage());
//				return redirectView;
//			}
//			model.addAttribute("res", res);
//		}
		
//		return redirectView;
		return null;
	}
}
