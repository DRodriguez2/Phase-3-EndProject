package com.HCL.Phase3.TaskManager.Task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HCL.Phase3.TaskManager.User.User;

public interface TaskRepository extends JpaRepository<Task, String>{
	public List<Task> findAllByUser(User user); 
	public Task findById(Long id);
}
