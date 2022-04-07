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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatLearning.EmployeeManagement.entity.Employee;
import com.greatLearning.EmployeeManagement.model.SearchCriteria;
import com.greatLearning.EmployeeManagement.serviceimpl.EmployeeServiceImpl;

@Controller
@RequestMapping("/employeeSearch")
public class EmployeeSearchController {

	@Autowired
	private EmployeeServiceImpl employeeService;
	
	@Autowired
	@Qualifier(value = "employeeSearchValidator")
	private Validator employeesearchvalidator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(employeesearchvalidator);
	}
	
	@RequestMapping("/searchemployee")
	public String searchBook(Model theModel,@RequestParam(name = "userstatus", defaultValue = "true") String userstatus,
			@RequestParam(name = "loginuser", defaultValue = "GUEST") String loginuser,
			@ModelAttribute("searchEmployeeCriteria")  @Validated SearchCriteria searchEmployeeCriteriaS,
			BindingResult binding) {
		String message="";
		String addbookbuttontxt="Add Book ";
		String addreviewbuttontxt="Add Review ";
		int totalPages=1;
		
		if (userstatus.contains("false")) {
			message="Dear Guest, Paginated Search fetched";
			loginuser="GUEST";
			addbookbuttontxt="Add Book-Disabled ";
			addreviewbuttontxt="Add Review-Disabled ";
		}else {
			message="Dear User, Paginated Search fetched";
			loginuser="USER";
		}
		
		if (binding.hasErrors()) {
			return "main-menu";
		}
		
		List<Employee> employees =employeeService.searchByOrderByPaginated(
				searchEmployeeCriteriaS.getSearchBy(),
				searchEmployeeCriteriaS.getSearchByValue(),
				searchEmployeeCriteriaS.getSortBy(),
				searchEmployeeCriteriaS.getSortOrder(),
				searchEmployeeCriteriaS.getPageSize(),
				searchEmployeeCriteriaS.getPageNo(), totalPages);
		
		SearchCriteria searchCriteria=new SearchCriteria(employeeService.getTotalPages(),"Employee");
		searchCriteria.setPageNoList(employeeService.getTotalPages());
		searchCriteria.setExistingSearchCriteria(searchEmployeeCriteriaS);
		
		//theModel.addAttribute("employees", employees);
		theModel.addAttribute("Employees", employees);
		theModel.addAttribute("userstatus", userstatus);
		theModel.addAttribute("loginuser", loginuser);
		theModel.addAttribute("message", message);
		theModel.addAttribute("searchCriteria", searchCriteria);
		theModel.addAttribute("addbookbuttontxt",addbookbuttontxt);
		theModel.addAttribute("addreviewbuttontxt",addreviewbuttontxt);
		return "main-menu";
	}
}
