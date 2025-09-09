package com.eastnets.taskmanagement.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("Task Management Manager is running");
		System.out.println("http://localhost:8080");
	}
//
//	@Bean
//	CommandLineRunner seedData(UserService userService, TaskService taskService) {
//		return args -> {
//			Users admin = new Users("admin", "admin", "admin@gmail.com", true);
//			Users user = new Users("user", "user", "user@gmail.com", false);
//			userService.save(admin);
//			userService.save(user);
//			taskService.save(new Tasks("Task 1", LocalDateTime.now(), Priority.High, Status.Pending, admin, user));
//			taskService.save(new Tasks("Task 2", LocalDateTime.now(), Priority.Medium, Status.Pending, admin, user));
//			taskService.save(new Tasks("Task 3", LocalDateTime.now(), Priority.Low, Status.Pending, admin, user));
//		};
	}

	


