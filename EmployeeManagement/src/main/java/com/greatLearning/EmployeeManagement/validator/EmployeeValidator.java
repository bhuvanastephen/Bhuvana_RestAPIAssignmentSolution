package com.greatLearning.EmployeeManagement.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.greatLearning.EmployeeManagement.entity.Employee;

@Component
public class EmployeeValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void validate(Object obj, Errors err) {
		System.out.println(" inside user book validator");
		Employee employee =(Employee) obj;
		if (employee.getFirstName().length() == 0) {
			err.rejectValue("firstName", "ERROR.EMPTY", "FirstName is Empty");
		}else if (employee.getLastName().length() == 0) {
			err.rejectValue("lastName", "ERROR.EMPTY", "LastName is Empty");
		}else if (employee.getEmail().length() == 0) {
			err.rejectValue("email", "ERROR.EMPTY", "email is Empty");
		}else if (employee.getEmail().contains("@") == false) {
			err.rejectValue("email", "ERROR.EMPTY", "email is invalid");
		}
	}

}
