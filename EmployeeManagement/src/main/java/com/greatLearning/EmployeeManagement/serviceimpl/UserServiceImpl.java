package com.greatLearning.EmployeeManagement.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greatLearning.EmployeeManagement.entity.Role;
import com.greatLearning.EmployeeManagement.entity.User;
import com.greatLearning.EmployeeManagement.repository.UserRepository;
import com.greatLearning.EmployeeManagement.service.UserService;

import lombok.Getter;
import lombok.Setter;


@Service
@Getter
@Setter
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;

	@Value("${default.role}")
	private String role;
	
	private int totalPages;
	
//	@Override
//	@Transactional
//	public void addUser(User user) {
//	
//		System.out.println("inside UserServiceImpl");
//		Optional<User> existingUser = repo.findByName(user.getUsername().toLowerCase());
//		if (existingUser.isPresent()) {
//			System.out.println("User already present ");
//		} else {
//			Role roleobj =new Role(role);
//			user.setUsername(user.getUsername().toLowerCase());
//			user.setPassword(bCryptPasswordEncoder(user.getPassword()));
//			user.addRole(roleobj);
//			repo.save(user);
//			//repo.flush();
//			System.out.println("User Registered ");
//		}

//	}
	
	@Override
	@Transactional
	public void addUser(User user) {
		System.out.println("inside addUser" + user);
		Optional<User> existingUser = repo.findByName(user.getUsername().toLowerCase());
		if (existingUser.isPresent()) {
			System.out.println("User already present ");
		} else {
			User newUser=new User();
			Role roleobj ;
			newUser.setUsername(user.getUsername().toLowerCase());
			newUser.setPassword(bCryptPasswordEncoder(user.getPassword()));
			if (user.getRoles().size() == 0) {
				System.out.println("Role is null " + user.getRoles().toString());
			    roleobj =new Role(role);
			    newUser.addRole(roleobj);
			}else {
				List<Role> rolesobj =user.getRoles();
				System.out.println("Role not is null " + rolesobj.toString());
				newUser.addRoles(rolesobj);
			}
			repo.save(newUser);
			System.out.println("User Registered ");
		}

	}

	public String bCryptPasswordEncoder(String password) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);
		String encodedPassword = passwordEncoder.encode(password);
		System.out.println("Password is         : " + password);
		System.out.println("Encoded Password is : " + encodedPassword);

		return encodedPassword;
	}

	@Override
	@Transactional
	public void amendRole(int userid, Role role) {
		Optional<User> useropt = repo.findById(userid);
		if(useropt.isPresent()) {
			
			User user=useropt.get();
			if (checkIfRolePresent(user,role) == false) {
				user.addRole(role);
				repo.save(user);
				System.out.println("User Role updated " + user);
			}
		}
	}

	@Override
	@Transactional
	public void removeRole(int userid, Role role) {
		Optional<User> useropt = repo.findById(userid);
			if(useropt.isPresent()) {
			
			User user=useropt.get();
			if (checkIfRolePresent(user,role) == true) {
				user.removeRole(role);
				repo.save(user);
				System.out.println("User Role removed " + user);
			}
		}
	}
	
	public List<User> findAllUsers() {
		List<User> users=repo.findAll();
		return users;
	}

	@Override
	@Transactional
	public User findById(int userid) {
		Optional<User> userOpt=repo.findById(userid);
		if (userOpt.isPresent()) {
			return userOpt.get();
		}else {
			return null;
		}
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		repo.deleteById(theId);
	}
	
	@Override
	@Transactional
	public void updateUser(User user) {
		System.out.println("inside UserServiceImpl");
		user.setUsername(user.getUsername().toLowerCase());
		user.setPassword(bCryptPasswordEncoder(user.getPassword()));
		repo.save(user);
		System.out.println("User Updated " + user);

	}
	
	@Override
	@Transactional
	public List<User> searchByOrderByPaginated(String searchBy,String searchByValue,
			String sortBy,String sortByOrder, 
			String pageSize, String pageNo, int totalPages) {
		System.out.println("searchByOrderByPaginated ");
		System.out.println("searchBy :" + searchBy);
		System.out.println("searchByValue :" + searchByValue);
		System.out.println("orderBy :" + sortBy);
		System.out.println("sortByOrder :" + sortByOrder);
		System.out.println("pageSize :" + pageSize);
		System.out.println("pageNo :" + pageNo);
		
		
		User userPagedSearchBy = new User();
		ExampleMatcher exampleMatcher= ExampleMatcher.matching()
				.withMatcher("username", ExampleMatcher.GenericPropertyMatchers.exact())
				.withIgnorePaths("username","password", "roles");
		if (searchBy.contains("id") & searchByValue.length()>0 ) {
			try {
				System.out.println("search by userid :" + searchByValue);
				userPagedSearchBy.setId(Integer.parseInt(searchByValue) );
				exampleMatcher=ExampleMatcher.matching()
						.withMatcher("id", ExampleMatcher.GenericPropertyMatchers.exact())
						.withIgnorePaths("username","password", "roles");
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
		}else if (searchBy.contains("username") & searchByValue.length()>0 ) {
			System.out.println("search by user-name :" + searchByValue);
			userPagedSearchBy.setUsername(searchByValue);
			exampleMatcher=ExampleMatcher.matching()
					.withMatcher("username", ExampleMatcher.GenericPropertyMatchers.exact())
					.withIgnorePaths("id","password", "roles");
		}else if (searchBy.contains("password") & searchByValue.length()>0 ) {
			userPagedSearchBy.setUsername(searchByValue);
			exampleMatcher=ExampleMatcher.matching()
					.withMatcher("password", ExampleMatcher.GenericPropertyMatchers.exact())
					.withIgnorePaths("id","username", "roles");
		} else if (searchBy.contains("roles") & searchByValue.length()>0 ) {
			List<Role> roles= new ArrayList<>();
			Role role = new Role();
			role.setName(searchByValue);
			roles.add(role);
			userPagedSearchBy.setRoles(roles);
			exampleMatcher=ExampleMatcher.matching()
					.withMatcher("roles", ExampleMatcher.GenericPropertyMatchers.exact())
					.withIgnorePaths("id","username", "password");
		} 
		System.out.println("1" );
		Direction direction=null;
		if (sortByOrder.contains("ASC")) {
			direction=Direction.ASC;
		}else {
			direction=Direction.DESC;
		}
	
		System.out.println("2" );
		Pageable customPage = PageRequest.of(
								Integer.parseInt(pageNo)-1, 
								Integer.parseInt(pageSize), 
								Sort.by(direction,sortBy));
		System.out.println("3" );
		Page<User> pageofUsers;
		List<User> users;
		if (searchByValue.length() == 0) {
			System.out.println("4" );
			pageofUsers =  repo.findAll(customPage);
			System.out.println("5" );
		}
		else {
			System.out.println("6" );
			Example<User> example = Example.of(userPagedSearchBy, exampleMatcher);
			pageofUsers =  repo.findAll(example,customPage);
			System.out.println("7" );
			}
		System.out.println("Pages" + pageofUsers.toString());
		users = pageofUsers.getContent();
		this.totalPages = pageofUsers.getTotalPages();
		return users;		
	}
	
	@Override
	@Transactional
	public boolean checkIfRolePresent(User user, Role role) {
		System.out.println("inside UserServiceImpl");
		boolean roleAlreadyPresent=false;
		List<Role> userRole=user.getRoles();
		for (Role eachRole : userRole) {
			if(eachRole.getName().contains(role.getName())){
				System.out.println("Role Already assigned");
				roleAlreadyPresent=true;
			}
		}
		
		return roleAlreadyPresent;

	}
}
