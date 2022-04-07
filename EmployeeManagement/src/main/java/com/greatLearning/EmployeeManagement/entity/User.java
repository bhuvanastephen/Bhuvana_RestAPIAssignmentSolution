package com.greatLearning.EmployeeManagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "username")
	private String username;
	
	@Column (name = "password")
	private String password;
	
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER)
	//@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( 
			name = "users_roles", 
			joinColumns = @JoinColumn( name="id"),
			inverseJoinColumns = @JoinColumn(name="role_id"))
	List<Role> roles= new ArrayList<>();

		    
	public void addRole(Role role ){
		System.out.println("Role passed for addRole " + role);
		if (this.roles == null){
            this.roles = new ArrayList<>();
        }
        this.roles.add(role);
	}
	
	public void addRoles(List<Role> roleslist ){
		System.out.println("Role passed for addRoles " + roles);
		if (this.roles == null){
            this.roles = new ArrayList<>();
        }
		for(Role role : roleslist){
			this.roles.add(role);
		}
	}

	public void removeRole(Role role) {
		List<Role> newRoleList = new ArrayList<>();
		for (Role roleIt : this.roles) {
			if (roleIt.getName().contains(role.getName())) {
				System.out.println("Role " + roleIt.getName() + " Removed for user:" + this.getUsername());
			}else {
				newRoleList.add(roleIt);
			}
		}
		this.roles=newRoleList;
	}
	
}
