package com.greatLearning.EmployeeManagement.validator;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.greatLearning.EmployeeManagement.entity.Role;
import com.greatLearning.EmployeeManagement.repository.RoleRepository;

public class RoleValidator implements Validator{

	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	@Override
	public void validate(Object obj, Errors err) {
		System.out.println(" Role validation");
		Role role=(Role) obj;
		Optional<Role> existingRole = roleRepo.findByName(role.getName().toUpperCase());
		if (existingRole.isPresent()) {
			System.out.println("Role already present " + existingRole.get().getName());
			err.rejectValue("name", "ROLE.ALREADY-PRESENT", "Role already present");
		}else if (role.getName().length() == 0){
			err.rejectValue("name", "ERROR.EMPTY", "Role is Empty");
		}
	}
}
