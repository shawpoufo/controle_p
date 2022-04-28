package com.controle.patient;

import com.controle.patient.repositories.PatientRepository;
import com.controle.patient.security.services.SecurityServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PatientApplication {
	@Autowired
	SecurityServiceImpl service;

	public static void main(String[] args) {
		SpringApplication.run(PatientApplication.class, args);
	}
	@Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }
	@Bean
	CommandLineRunner commandLineRunner(PatientRepository repository){
		
		return args -> {
			service.saveNewRole("ADMIN", "administration");
			service.saveNewRole("USER", "scrolling");

			service.saveNewUser("admin", "admin", "admin");
			service.saveNewUser("mitah", "0123", "0123");

			service.addRoleToUser("admin", "ADMIN");
			service.addRoleToUser("admin", "USER");
			service.addRoleToUser("mitah", "USER");

		};
	}
}
