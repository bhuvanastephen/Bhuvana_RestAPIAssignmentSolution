package com.greatLearning.EmployeeManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatLearning.EmployeeManagement.entity.Employee;


@Service
public interface EmployeeService {

	public List<Employee> findAll();
	
	public Employee findById(int id);
	
	public Employee save(Employee employee);
	
	public Employee deleteById(int id);
	
	public List<Employee> searchByOrderByPaginated(String searchBy,String searchByValue, 
			String sortBy,String sortByOrder, 
			String pageSize, String pageNo,
			int totalPages);

	public List<Employee> findByFirstName(String firstname);
	public List<Employee> getSortByOrdered(String sortBy,String sortByOrder);
}
