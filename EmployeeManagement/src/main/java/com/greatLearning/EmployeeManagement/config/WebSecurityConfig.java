package com.greatLearning.EmployeeManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatLearning.EmployeeManagement.security.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public UserDetailsService userDetailsService() {
		return new MyUserDetailsService();
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider =new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(passwordEncoder());
		authProvider.setUserDetailsService(userDetailsService());
		return authProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http	
		.authorizeRequests()
		//rest------start----
		.antMatchers("/rest/addemployee").hasAuthority("ADMIN")
		//rest-------end-----
		.antMatchers("/roles").hasAuthority("ADMIN")
		
		//h2-console- All
		.antMatchers("/h2-console/").permitAll()
		
		//HomePage-All
		.antMatchers("/","/home").permitAll()
		
		//SignUp New - All
		.antMatchers("/home/signup","/home/signup-form").permitAll()
		
		//Unauthorised 403- All
		.antMatchers("/employee/403").permitAll()
		
		//Employee Add- ADMIN
		.antMatchers("/employee/save","/employee/showFormForAdd").hasAnyAuthority("ADMIN")
        
		//Employee Delete & Update - ADMIN
		.antMatchers("/employee/showFormForUpdate","/employee/delete").hasAuthority("ADMIN")
        
		//User Update / Delete - ADMIN
		.antMatchers("/user/usermenu","/user/showFormForUpdateUser","/user/deleteUser").hasAuthority("ADMIN")
				
		//Role Add/Amend UserRole/ Delete UserRole- ADMIN
		.antMatchers("user/showFormForAddRole","user/addRole","user/amendrole","user/removerole").hasAuthority("ADMIN")
       
        .anyRequest().authenticated().and().httpBasic()
        .and()
        .formLogin().loginProcessingUrl("/login").successForwardUrl("/employee/mainmenu").permitAll()
        .and()
        .logout().logoutSuccessUrl("/home").permitAll()
        .and()
        .exceptionHandling().accessDeniedPage("/employee/403")
        .and().cors().and().csrf().disable();
		
	}
}
