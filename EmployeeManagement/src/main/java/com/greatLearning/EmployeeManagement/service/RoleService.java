package com.greatLearning.EmployeeManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatLearning.EmployeeManagement.entity.Role;
import com.greatLearning.EmployeeManagement.entity.User;

@Service
public interface RoleService {
	
	public List<Role> findAllRoles();
	public void addRole(Role role);
}
