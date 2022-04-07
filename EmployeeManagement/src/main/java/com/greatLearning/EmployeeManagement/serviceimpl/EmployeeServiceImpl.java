package com.greatLearning.EmployeeManagement.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.greatLearning.EmployeeManagement.entity.Employee;
import com.greatLearning.EmployeeManagement.repository.EmployeeRepository;
import com.greatLearning.EmployeeManagement.service.EmployeeService;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;
	
	private int totalPages;
	
	@Override
	@Transactional
	public List<Employee> findAll() {
		List<Employee> students = employeeRepo.findAll();
		return students;
	}

	@Override
	@Transactional
	public Employee findById(int id) {
		Employee employee= employeeRepo.findById(id).get();
		return employee;
	}

	@Override
	@Transactional
	public Employee save(Employee employee) {
		employeeRepo.save(employee);
		return employee;
	}

	@Override
	@Transactional
	public Employee deleteById(int id) {
		Employee employee= employeeRepo.findById(id).get();
		employeeRepo.deleteById(id);
		return employee;

	}
	
	@Override
	@Transactional
	public List<Employee> searchByOrderByPaginated(String searchBy,String searchByValue,
			String sortBy,String sortByOrder, 
			String pageSize, String pageNo, int totalPages) {
		System.out.println("searchByOrderByPaginated ");
		System.out.println("searchBy :" + searchBy);
		System.out.println("searchByValue :" + searchByValue);
		System.out.println("orderBy :" + sortBy);
		System.out.println("sortByOrder :" + sortByOrder);
		System.out.println("pageSize :" + pageSize);
		System.out.println("pageNo :" + pageNo);
		
	
		Employee libraryPagedSearchBy = new Employee();
		ExampleMatcher exampleMatcher= ExampleMatcher.matching()
				.withMatcher("employeetest", ExampleMatcher.GenericPropertyMatchers.exact())
				.withIgnorePaths("firstName","lastName", "email");
		if (searchBy.contains("employeeid") & searchByValue.length()>0 ) {
			System.out.println("search by employeeid :" + searchByValue);
			libraryPagedSearchBy.setEmployeeid((Integer.parseInt(searchByValue)));
			exampleMatcher=ExampleMatcher.matching()
					.withMatcher("employeetest", ExampleMatcher.GenericPropertyMatchers.exact())
					.withIgnorePaths("firstName","lastName", "email");
		}else if (searchBy.contains("firstName") & searchByValue.length()>0 ) {
			libraryPagedSearchBy.setFirstName(searchByValue);
			exampleMatcher=ExampleMatcher.matching()
					.withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.exact())
					.withIgnorePaths("employee_id","lastName", "email");
		} else if (searchBy.contains("lastName") & searchByValue.length()>0) {
			libraryPagedSearchBy.setLastName(searchByValue);
			exampleMatcher=ExampleMatcher.matching()
					.withMatcher("lastName", ExampleMatcher.GenericPropertyMatchers.exact())
					.withIgnorePaths("firstName","employee_id", "email");
		} else if (searchBy.contains("email") & searchByValue.length()>0) {
			libraryPagedSearchBy.setEmail(searchByValue);
			 exampleMatcher=ExampleMatcher.matching()
					.withMatcher("email", ExampleMatcher.GenericPropertyMatchers.exact())
					.withIgnorePaths("firstName","lastName", "employee_id");
		} 
		Direction direction=null;
		if (sortByOrder.contains("ASC")) {
			direction=Direction.ASC;
		}else {
			direction=Direction.DESC;
		}
	
		Pageable customPage = PageRequest.of(Integer.parseInt(pageNo)-1, Integer.parseInt(pageSize), Sort.by(direction,sortBy));
		Page<Employee> pageofBooks;
		List<Employee> books;
		if (searchByValue.length() == 0)  {
			pageofBooks =  employeeRepo.findAll(customPage);
		}else {
			Example<Employee> example = Example.of(libraryPagedSearchBy, exampleMatcher);
			pageofBooks =  employeeRepo.findAll(example,customPage);
		}
		System.out.println("Pages" + pageofBooks.toString());
		books = pageofBooks.getContent();
		this.totalPages = pageofBooks.getTotalPages();
		return books;		
	}

	@Override
	public List<Employee> findByFirstName(String firstname) {
		System.out.println("Search for " + firstname);
		List<Employee> employee= employeeRepo.findByFirstname(firstname);
		System.out.println("output " + employee.toString());
		return employee;
	}
	
	public List<Employee> getSortByOrdered(String sortBy,String sortByOrder) {		 
		Direction direction=null;
		if (sortByOrder.contains("ASC")) {
			direction=Direction.ASC;
		}else {
			direction=Direction.DESC;
		}

		List<Employee> employees =employeeRepo.findAll(Sort.by(direction,sortBy));
		return employees;
	}

}
