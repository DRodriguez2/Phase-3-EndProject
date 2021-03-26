package com.HCL.Phase3.TaskManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.HCL.Phase3.TaskManager.Task.Severity;
import com.HCL.Phase3.TaskManager.Task.Task;
import com.HCL.Phase3.TaskManager.Task.TaskService;
import com.HCL.Phase3.TaskManager.User.User;
import com.HCL.Phase3.TaskManager.User.UserService;

@SpringBootApplication
public class TaskManagerApplication implements CommandLineRunner {

	@Autowired private UserService userService;
	@Autowired private TaskService taskService;
	
	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		/**
		 * Insert sample data
		 */
		LocalDate date = LocalDate.now(); 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		
		User user1 = new User("user1", "password");
		User user2 = new User("user2", "password");
		User user3 = new User("user3", "password");
		
		Task task1 = new Task("first task", date.format(formatter).toString(), date.format(formatter).toString(), "description 1", "user1@email.com", Severity.LOW, user1);
		Task task2 = new Task("second task", date.format(formatter).toString(), date.format(formatter).toString(), "description 2", "user1@email.com", Severity.HIGH, user1);
		Task task3 = new Task("third task", date.format(formatter).toString(), date.format(formatter).toString(), "description 3", "user2@email.com", Severity.MEDIUM, user2);
		Task task4 = new Task("third task", date.format(formatter).toString(), date.format(formatter).toString(), "description 4", "user3@email.com", Severity.LOW, user3);
		
		user1.addTask(task1);
		user1.addTask(task2);
		user2.addTask(task3);
		user3.addTask(task4);
		
		userService.insertUser(user1);
		userService.insertUser(user2);
		userService.insertUser(user3);
		
		taskService.insertTask(task1);
		taskService.insertTask(task2);
		taskService.insertTask(task3);
		taskService.insertTask(task4);
	}
}
