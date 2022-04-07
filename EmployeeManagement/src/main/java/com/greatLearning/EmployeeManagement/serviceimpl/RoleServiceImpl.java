package com.greatLearning.EmployeeManagement.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatLearning.EmployeeManagement.entity.Role;
import com.greatLearning.EmployeeManagement.repository.RoleRepository;
import com.greatLearning.EmployeeManagement.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository repo;
	
	@Override
	public List<Role> findAllRoles() {
		List<Role> roles=repo.findAll();
		return roles;
	}

	@Override
	public void addRole(Role role) {
		System.out.println("Role is " + role);
		role.setName(role.getName().toUpperCase());
		repo.save(role);
		System.out.println("New Role Saved " + role.getName());
	}

	
}
