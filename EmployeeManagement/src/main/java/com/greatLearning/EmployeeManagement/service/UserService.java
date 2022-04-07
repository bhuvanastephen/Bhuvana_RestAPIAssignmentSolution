package com.greatLearning.EmployeeManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatLearning.EmployeeManagement.entity.Role;
import com.greatLearning.EmployeeManagement.entity.User;


@Service
public interface UserService {
	
	public List<User> findAllUsers();
	public void addUser(User user);
	public User findById(int userid);
	public void amendRole(int userid, Role role);
	
	public void deleteById(int theId);
	void updateUser(User user);
	
	public List<User> searchByOrderByPaginated(String searchBy,String searchByValue, 
			String sortBy,String sortByOrder, 
			String pageSize, String pageNo,
			int totalPages);
	boolean checkIfRolePresent(User user, Role role);
	void removeRole(int userId, Role role2);
}
