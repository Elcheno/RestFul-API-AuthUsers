package com.example;

import com.example.model.entity.RoleEntity;
import com.example.model.entity.UserEntity;
import com.example.model.types.ERole;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class SpringExampleApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringExampleApplication.class, args);
	}

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Bean
	CommandLineRunner init() {
		return args -> {
			UserEntity userEntity1 = UserEntity.builder()
					.email("admin@example.com")
					.name("Admin")
					.password(passwordEncoder.encode("admin"))
					.roles(Set.of(RoleEntity.builder()
							.name(ERole.valueOf(ERole.ADMIN.name())).build()))
					.build();

			UserEntity userEntity2 = UserEntity.builder()
					.email("user@example.com")
					.name("User")
					.password(passwordEncoder.encode("user"))
					.roles(Set.of(RoleEntity.builder()
							.name(ERole.valueOf(ERole.USER.name())).build()))
					.build();

			UserEntity userEntity3 = UserEntity.builder()
					.email("invited@example.com")
					.name("Invited")
					.password(passwordEncoder.encode("invited"))
					.roles(Set.of(RoleEntity.builder()
							.name(ERole.valueOf(ERole.INVITED.name())).build()))
					.build();

			userRepository.save(userEntity1);
			userRepository.save(userEntity2);
			userRepository.save(userEntity3);
		};
	}

}
