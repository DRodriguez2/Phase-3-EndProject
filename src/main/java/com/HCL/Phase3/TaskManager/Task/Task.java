package com.HCL.Phase3.TaskManager.Task;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.HCL.Phase3.TaskManager.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Task {
	
	@Id @GeneratedValue 
	private Long id;
	@ManyToOne @JoinColumn(name="username") @JsonIgnore
	private User user;
	
	@Enumerated(EnumType.STRING)
	private Severity severity;
	@Pattern(regexp="\\A(?!\\s*\\Z).+", message="Value required")
	@NotEmpty(message="Value required")
	private String taskName;
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@NotEmpty(message="Value required")
	private String startDate;
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@NotEmpty(message="Value required")
	private String endDate;
	@Pattern(regexp="\\A(?!\\s*\\Z).+", message="Value required")
	@NotEmpty(message="Value required")
	private String description;
	@Email(message="Please enter a valid email")
	@NotEmpty(message="Value required")
	private String email;
	
	public Task() {}

	public Task(String taskName, String startDate, String endDate, String descritpion, String email, Severity severity,
			User user) {
		super();
		this.taskName = taskName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = descritpion;
		this.email = email;
		this.severity = severity;
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Task: " + this.getTaskName() + "[ startDate:" + this.getStartDate() + ", endDate: " + this.getEndDate() 
		+ ", description: " + this.getDescription() + ", email: " + this.getEmail() + " ]";
	}
}
