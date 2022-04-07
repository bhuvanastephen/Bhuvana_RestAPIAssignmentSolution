package com.greatLearning.EmployeeManagement.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatLearning.EmployeeManagement.entity.Employee;
import com.greatLearning.EmployeeManagement.model.SearchCriteria;
import com.greatLearning.EmployeeManagement.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private SearchCriteria searchCriteria;
	
	@RequestMapping("/mainmenu")
	public String listBooks(Model theModel) {

		List<Employee> employees = employeeService.findAll();
		
		searchCriteria=new SearchCriteria(1,"Employee");
		
		theModel.addAttribute("searchCriteria", searchCriteria);
		theModel.addAttribute("Employees", employees);

		return "main-menu";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Employee employee = new Employee();

		theModel.addAttribute("Employee", employee);

		return "Employee-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,
			Model theModel) {

		Employee employee = employeeService.findById(theId);

		theModel.addAttribute("Employee", employee);

		return "Employee-form";			
	}


	@PostMapping("/save")
	public String saveEmployee(@RequestParam("id") int id,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("email") String email) {

		System.out.println(id);
		Employee theEmployee;
		if(id!=0)
		{
			theEmployee=employeeService.findById(id);
			theEmployee.setFirstName(firstName);
			theEmployee.setLastName(lastName);
			theEmployee.setEmail(email);
		}
		else
			theEmployee=new Employee(firstName, lastName, email);
		employeeService.save(theEmployee);


		return "redirect:/employee/mainmenu";

	}


	@RequestMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {

		employeeService.deleteById(theId);

		return "redirect:/employee/mainmenu";

	}
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", 
			"You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}


	
}
