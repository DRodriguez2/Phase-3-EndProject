package com.HCL.Phase3.TaskManager.Task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.HCL.Phase3.TaskManager.User.User;
import com.HCL.Phase3.TaskManager.User.UserRepository;

@Service
public class TaskService {

	@Autowired private UserRepository userRepository;
	@Autowired private TaskRepository taskRepository;
	
	public List<Task> getAllTasksByUsername(@PathVariable String username) {
		User user = userRepository.findById(username).get();
		return taskRepository.findAllByUser(user);
	} 
	
	public List<Task> insertTask(Task task) {
		taskRepository.save(task);
		return taskRepository.findAllByUser(task.getUser());
	}
	
	public Task findTaskById(Long id) {
		return taskRepository.findById(id);
	}
	
	public void deleteTask(Task task) {
		taskRepository.delete(task);
	}
	
}
