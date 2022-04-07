package com.greatLearning.EmployeeManagement.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greatLearning.EmployeeManagement.entity.Role;
import com.greatLearning.EmployeeManagement.entity.User;
import com.greatLearning.EmployeeManagement.model.SearchCriteria;
import com.greatLearning.EmployeeManagement.serviceimpl.RoleServiceImpl;
import com.greatLearning.EmployeeManagement.serviceimpl.UserServiceImpl;

@Controller
@RequestMapping("/userSearch")
public class UserSearchController {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	@Qualifier(value = "userSearchValidator")
	private Validator userSearchValidator;
	
	@Autowired
	private RoleServiceImpl roleService;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(userSearchValidator);
	}
	
	//userSearch/searchuser
	@RequestMapping("/searchuser")
	public String searchBook(Model theModel,
			@ModelAttribute("searchCriteria")  @Validated SearchCriteria searchCriteriaS,
			BindingResult binding) {

		int totalPages=1;
			
		if (binding.hasErrors()) {
			return "user-menu";
		}
		
		Role roleAttribute =new Role();
		List<Role> roles = roleService.findAllRoles();
		LinkedHashMap<String, String> rolelist = new LinkedHashMap<>();
		for (Role role : roles) {
			rolelist.put(role.getName(), role.getName());
		}
		
		List<User> users =userService.searchByOrderByPaginated(
				searchCriteriaS.getSearchBy(),
				searchCriteriaS.getSearchByValue(),
				searchCriteriaS.getSortBy(),
				searchCriteriaS.getSortOrder(),
				searchCriteriaS.getPageSize(),
				searchCriteriaS.getPageNo(), totalPages);
		
		SearchCriteria searchCriteria=new SearchCriteria(userService.getTotalPages(),"User");
		searchCriteria.setPageNoList(userService.getTotalPages());
		searchCriteria.setExistingSearchCriteria(searchCriteriaS);
		
		theModel.addAttribute("Users", users);
		theModel.addAttribute("searchCriteria", searchCriteria);

		theModel.addAttribute("Role", roleAttribute);
		theModel.addAttribute("rolelist", rolelist);
		
		return "user-menu";
	}

}
