package com.greatLearning.EmployeeManagement.validator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.greatLearning.EmployeeManagement.entity.User;
import com.greatLearning.EmployeeManagement.repository.UserRepository;



@Component
public class UserValidator implements Validator{
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	@Override
	public void validate(Object obj, Errors err) {
		System.out.println(" user validation");
		User user=(User) obj;
		Optional<User> existingUser = userRepo.findByName(user.getUsername().toLowerCase());
		if (existingUser.isPresent()) {
			System.out.println("User already present ");
			err.rejectValue("username", "USER.ALREADY-PRESENT", "User already present");
		}else if (user.getUsername().length() == 0){
			err.rejectValue("username", "ERROR.EMPTY", "Username is Empty");
		}else if (user.getPassword().length() == 0){
			err.rejectValue("password", "ERROR.EMPTY", "Password is Empty");
		}else if (user.getPassword().length() < 6){
			err.rejectValue("password", "ERROR.EMPTY", "Password Length Should be greater than 6");
		}
	}

}
