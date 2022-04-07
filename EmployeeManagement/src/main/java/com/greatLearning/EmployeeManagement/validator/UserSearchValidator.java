package com.greatLearning.EmployeeManagement.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.greatLearning.EmployeeManagement.model.SearchCriteria;

@Component
public class UserSearchValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void validate(Object obj, Errors errors) {
		SearchCriteria searchbookCriteria = (SearchCriteria) obj;
		if (searchbookCriteria.getSearchBy().contains("id") & searchbookCriteria.getSearchByValue().length() > 0
				& isInteger(searchbookCriteria.getSearchByValue()) == false ) {
			errors.rejectValue("searchByValue", "ERROR.FORMAT", "User id should be a number");
		}
	}

	public  boolean isInteger(String s) {	    
	    
	    try {
	      int iVal = Integer.parseInt(s);
	      return true;
	    }
	    catch(NumberFormatException e) {
	      System.out.println("Cannot parse the string to integer");
	    }
	    return false;
	  }
}
