package com.greatLearning.EmployeeManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greatLearning.EmployeeManagement.entity.Employee;
import com.greatLearning.EmployeeManagement.entity.Role;
import com.greatLearning.EmployeeManagement.entity.User;
import com.greatLearning.EmployeeManagement.service.EmployeeService;
import com.greatLearning.EmployeeManagement.service.RoleService;
import com.greatLearning.EmployeeManagement.service.UserService;

@RestController
@RequestMapping("/rest")
public class RestControll {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmployeeService empService;
	
	
	//1.Add New Role
	@PostMapping(path="/addrole")
	public String addRole(@RequestBody  Role role ) {
		roleService.addRole(role);
		return ("Role Added : " + role.getName()) ;
	}
	//2.Add New User
	@PostMapping(path="/adduser")
	public String addUser(@RequestBody  User user ) {
		userService.addUser(user);
		return ("User Added : " + user.getUsername()) ;
	}
	//3.Add New Employee -Only Admin
	@PostMapping(path="/addemployee")
	public String addEmployee(@RequestBody Employee employee ) {
		empService.save(employee);
		return ("User Added : " + employee.getFirstName()) ;
	}
	//4.List All Employees
	@GetMapping(path="/employees")
	public List<Employee> listEmployees() {
		List<Employee> employees = empService.findAll();
		return employees ;
	}
	
	//5.Fetch Employee Based on Id
	@GetMapping(path="/employees/{empid}")
	public Employee listEmployeesById(@PathVariable("empid") String empId) {
		Employee employee = empService.findById(Integer.parseInt(empId));
		return employee ;
	}
	
	//6.Update Existing Employee
	@PutMapping(path="/employees")
	public Employee UpdateEmployee(@RequestBody Employee employee) {
		empService.save(employee);
		return employee ;
	}
	
	//7.Delete Existing Employee
	@DeleteMapping(path="/employees/{empid}")
	public String  deleteEmployee(@PathVariable("empid") String empId) {
		Employee employee = empService.deleteById(Integer.parseInt(empId));
		if (employee != null) {
			return "Deleted employee id -"  + employee.getEmployeeid() ;
		}else {
			return "Employee not found ";
		}
		
	}
	//8.Fetch Employee Based on FirstName
	@GetMapping(path="/employees/search/{firstname}")
	public List<Employee> getEmployeeByFirstName(@PathVariable("firstname") String firstname) {
		System.out.println("first Search for " + firstname);
		List<Employee> employee = empService.findByFirstName(firstname);
		return employee ;
	}
	
	//9.List Employee Sorted -ASC/DESC
	@GetMapping(path="/employees/sort")
	public List<Employee> getEmployeeSorted(@RequestParam("order") String direction) {
		List<Employee> employees = empService.getSortByOrdered("firstName",direction);
		return employees ;
	}
	
	
}
