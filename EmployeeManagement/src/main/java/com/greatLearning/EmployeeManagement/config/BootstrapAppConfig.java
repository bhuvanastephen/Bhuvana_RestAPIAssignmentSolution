package com.greatLearning.EmployeeManagement.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import com.github.javafaker.Faker;
import com.greatLearning.EmployeeManagement.entity.Employee;
import com.greatLearning.EmployeeManagement.entity.Role;
import com.greatLearning.EmployeeManagement.repository.EmployeeRepository;
import com.greatLearning.EmployeeManagement.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;


@Configuration
@Slf4j
public class BootstrapAppConfig implements ApplicationListener<ApplicationReadyEvent> {
    private final EmployeeRepository empRepo;

    private final UserRepository userRepository;

    private final Faker faker = new Faker();

    public BootstrapAppConfig(EmployeeRepository empRepo, UserRepository userRepository) {
        this.empRepo = empRepo;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
      System.out.println("=========Application-initialized==========");
      for (int i = 0; i < 200; i ++){

         Employee employee = new Employee(
        		 faker.name().firstName(),
        		 faker.name().lastName(),
        		 faker.internet().emailAddress());
        		 
       
         this.empRepo.save(employee);
         
      }
      
      System.out.println("=========Application-initialized==========");
    }

}
