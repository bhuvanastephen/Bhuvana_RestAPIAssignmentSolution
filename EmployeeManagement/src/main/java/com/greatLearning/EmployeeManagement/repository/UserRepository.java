package com.greatLearning.EmployeeManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greatLearning.EmployeeManagement.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.username= ?1")
	User getUserByName(String username);

	@Query(value ="select * from users u where u.username = :uname",nativeQuery = true)
	Optional<User> findByName(String uname);
	
	
	
}