package com.HCL.Phase3.TaskManager.User;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.HCL.Phase3.TaskManager.Task.Task;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class User {

	@Id 
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<Task> tasks = new ArrayList<>();
	
	public User() {};
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public void addTask(Task task) {
		this.tasks.add(task);
	}
	
	public void removeTask(Task task) {
		this.tasks.remove(task);
	}
}
