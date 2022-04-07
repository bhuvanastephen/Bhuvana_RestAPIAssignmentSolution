package com.greatLearning.EmployeeManagement.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greatLearning.EmployeeManagement.entity.Role;
import com.greatLearning.EmployeeManagement.entity.User;
import com.greatLearning.EmployeeManagement.model.SearchCriteria;
import com.greatLearning.EmployeeManagement.serviceimpl.RoleServiceImpl;
import com.greatLearning.EmployeeManagement.serviceimpl.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private RoleServiceImpl roleService;
	
	
	@Autowired
	private SearchCriteria searchCriteria;
	
	@RequestMapping("/usermenu")
	public String listBooks(Model theModel) {

		List<User> users = userService.findAllUsers();
		Role roleAttribute =new Role();
		List<Role> roles = roleService.findAllRoles();
		LinkedHashMap<String, String> rolelist = new LinkedHashMap<>();
		for (Role role : roles) {
			rolelist.put(role.getName(), role.getName());
		}
					
		searchCriteria=new SearchCriteria(1,"User");
		
		theModel.addAttribute("searchCriteria", searchCriteria);
		theModel.addAttribute("Users", users);
		theModel.addAttribute("Role", roleAttribute);
		theModel.addAttribute("rolelist", rolelist);
		

		return "user-menu";
	}
	
	//showFormForAddRole
	@RequestMapping("/showFormForAddRole")
	public String showFormForAdd(Model theModel) {

		Role role = new Role();

		theModel.addAttribute("Role", role);

		return "Role-form";
	}
	
	@PostMapping(path="/addRole")
	public String addRole(Model model,@RequestBody @ModelAttribute("Role") @Validated Role role ,BindingResult binding) {
		
		System.out.println("inside addRole");
		if (binding.hasErrors()) {
			return "Role-form";
		}
		roleService.addRole(role);
		return "redirect:/user/usermenu";
	}
	
	@GetMapping(path="/roles")
	@ResponseBody
	public List<Role> testaddRole() {
		
		System.out.println("inside roles");
		
		return roleService.findAllRoles();
		
	}
	
	//amendrole?userId=${tempUser.id}
	@RequestMapping("/amendrole")
	public String amendRole(Model model,
			@RequestParam(name = "userId", defaultValue = "0") int userId,
			Role role) {
		
		System.out.println("userId is " + userId);
		userService.amendRole(userId,role);
		return "redirect:/user/usermenu";
	}
	
	//removerole
	@RequestMapping("/removerole")
	public String removerole(Model model,
			@RequestParam(name = "userId", defaultValue = "0") int userId,
			Role role) {
		
		System.out.println("userId is " + userId);
		userService.removeRole(userId,role);
		return "redirect:/user/usermenu";
	}
	
    //showFormForUpdateUser?userId=${tempUser.id}
	@RequestMapping("/showFormForUpdateUser")
	public String showFormForUpdate(@RequestParam("userId") int theId,
			Model theModel) {

		User user = userService.findById(theId);
		user.setPassword("......");
		theModel.addAttribute("User", user);

		return "UserUpdate-form";			
	}


	@PostMapping("/updateUser")
	public String saveEmployee(@RequestParam("id") int id,
			@RequestParam("username") String username,
			@RequestParam("password") String password) {

		System.out.println(id);
		User user=userService.findById(id);
		user.setUsername(username);
		user.setPassword(password);
		userService.updateUser(user);

		return "redirect:/user/usermenu";

	}
	
	//deleteUser?userId=${tempUser.id}
	@RequestMapping("/deleteUser")
	public String delete(@RequestParam("userId") int theId) {

		userService.deleteById(theId);

		return "redirect:/user/usermenu";

	}
}


