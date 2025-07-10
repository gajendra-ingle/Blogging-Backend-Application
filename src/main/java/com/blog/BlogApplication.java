package com.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blog.config.AppConstants;
import com.blog.entities.Role;
import com.blog.repositories.RoleRepository;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	private static final Logger logger = LoggerFactory.getLogger(BlogApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) {
		logger.info("Encoded password for 'Gaj@1234': {}", passwordEncoder.encode("Gaj@1234"));

		try {
			if (roleRepository.count() == 0) {
				Role roleAdmin = new Role();
				roleAdmin.setId(AppConstants.ADMIN_USER);
				roleAdmin.setName("ROLE_ADMIN");

				Role roleNormal = new Role();
				roleNormal.setId(AppConstants.NORMAL_USER);
				roleNormal.setName("ROLE_NORMAL");

				List<Role> roles = List.of(roleAdmin, roleNormal);
				roleRepository.saveAll(roles).forEach(role -> logger.info("Inserted Role: {}", role.getName()));
			} else {
				logger.info("Roles already exist. Skipping role creation.");
			}
		} catch (Exception e) {
			logger.error("Error during role initialization: {}", e.getMessage(), e);
		}
	}
}
