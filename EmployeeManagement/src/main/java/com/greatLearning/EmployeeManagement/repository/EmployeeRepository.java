package com.greatLearning.EmployeeManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greatLearning.EmployeeManagement.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


	@Query(value ="select * from employee e where e.first_Name = :firstname",nativeQuery = true)
	List<Employee> findByFirstname(String firstname);
	
}
