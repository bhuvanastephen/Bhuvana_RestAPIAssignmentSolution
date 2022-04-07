package com.greatLearning.EmployeeManagement.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatLearning.EmployeeManagement.entity.Employee;
import com.greatLearning.EmployeeManagement.entity.User;
import com.greatLearning.EmployeeManagement.model.SearchCriteria;
import com.greatLearning.EmployeeManagement.service.EmployeeService;
import com.greatLearning.EmployeeManagement.service.UserService;

@Controller
//@RequestMapping("/home")
public class LoginController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	@Qualifier( value = "userValidator")
	private Validator uservalidator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(uservalidator);
	}
	
	@Autowired
	private SearchCriteria searchCriteria;
	
	@RequestMapping(path={"/home","/"})
	public String home(Model theModel) {
		System.out.println("home");
		return "home";
	}
	
	@RequestMapping(path="/home/signin-form")
	public String signinform(Model theModel) {
		System.out.println("signin formrequest recieved");
		return "redirect:/employee/mainmenu";
	}
	
	@RequestMapping(path="/home/signin")
	public String signin(Model theModel,@RequestParam(name = "userstatus", defaultValue = "true") String userstatus,
			@RequestParam(name = "loginuser", defaultValue = "GUEST") String loginuser) {
		
		return "redirect:/employee/mainmenu";
	}
	
	
	@RequestMapping(path="/home/signup-form")
	public String signupform(Model theModel) {
		System.out.println("signup formrequest recieved");
		User user= new User();
		theModel.addAttribute("user", user);
		return "signup-form";
	}
	
	
	@PostMapping(path="/home/signup")
	public String addCustomer(Model model,@ModelAttribute("user") @Validated User user ,BindingResult binding) {
		if (binding.hasErrors()) {
			return "signup-form";
		}
		userService.addUser(user);
		
		searchCriteria=new SearchCriteria(1,"User");
		List<Employee> employees =employeeService.findAll();
		model.addAttribute("Employees", employees);
		model.addAttribute("message", "Sign up sucessfull " + user.getUsername() +" , Welcome to Online Book Review System");
		model.addAttribute("searchCriteria", searchCriteria);
		return "redirect:/login/signin-form";
	}
}
