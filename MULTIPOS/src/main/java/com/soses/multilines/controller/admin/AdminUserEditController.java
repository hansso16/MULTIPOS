package com.soses.multilines.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.soses.multilines.api.user.EditUserRequest;
import com.soses.multilines.dto.EditUserDTO;
import com.soses.multilines.service.admin.UserEditDetailService;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class AdminUserEditController {

	private static final Logger log = LoggerFactory.getLogger(AdminUserEditController.class);

	private final UserEditDetailService userEditDetailsService;


	public AdminUserEditController(UserEditDetailService userEditDetailsService) {
		super();
		this.userEditDetailsService = userEditDetailsService;
	}
	
	@GetMapping("/admin/user/{userId}/edit")
	//@PreAuthorize("hasAuthority('USER_EDIT')")
	public String showEditUser(@PathVariable Integer userId, Model model) throws Exception {

		log.info("showEditUser(userId,model)");
	    EditUserRequest viewData = userEditDetailsService.getUserEditDetailsView(userId);

	    model.addAttribute("userForm", viewData.getEditUserDto());
	    model.addAttribute("roles", viewData.getRoleList());
	    model.addAttribute("privilegeMap", viewData.getPrivilegeByModule());

	    return "admin/user_edit";
	}
	
	@PostMapping("/admin/user/{userId}/edit")
	//@PreAuthorize("hasAuthority('USER_EDIT')")
	public String submitEditUser(
	        @ModelAttribute EditUserDTO form,
	        Model model, RedirectAttributes redirectAttributes) throws Exception {

		log.info("submitEditUser(form,model)");
	    try {
	    	userEditDetailsService.updateUser(form);
	    	redirectAttributes.addFlashAttribute("successMessage","User details updated successfully");
	        return "redirect:/admin/user/" + form.getUserId();

	    } catch (Exception ex) {
	        // reload same data
	    	EditUserRequest viewData = userEditDetailsService.getUserEditDetailsView(form.getUserId());

	        model.addAttribute("userForm", viewData.getEditUserDto());
	        model.addAttribute("roles", viewData.getRoleList());
	        model.addAttribute("privilegeMap", viewData.getPrivilegeByModule());
	        model.addAttribute("errorMessage", ex.getMessage());

	        return "admin/user_edit";
	    }
	}
}
