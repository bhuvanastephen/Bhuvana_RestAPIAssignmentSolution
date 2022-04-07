package com.greatLearning.EmployeeManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.greatLearning.EmployeeManagement.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	@Query(value ="select * from Roles r where r.name = :rname",nativeQuery = true)
	Optional<Role> findByName(String rname);



}
