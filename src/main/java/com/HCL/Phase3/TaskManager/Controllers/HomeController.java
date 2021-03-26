package com.HCL.Phase3.TaskManager.Controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.HCL.Phase3.TaskManager.Task.Task;
import com.HCL.Phase3.TaskManager.Task.TaskService;
import com.HCL.Phase3.TaskManager.User.User;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired private TaskService taskService;
	
	@RequestMapping()
	public String home(Authentication authentication, Model model, HttpServletRequest request) {
		model.addAttribute("task", new Task());
		model.addAttribute("editTask", new Task());
		return "home";
	}
	
	@PostMapping("/addTask")
	public String addTask(@Valid @ModelAttribute("task") Task task, BindingResult result, 
						  Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		List<Task> tasks;
		if(result.hasErrors()) { 
			logger.info("INVALID TASK");
			tasks = user.getTasks();
		}
		else { 
			logger.info("VALID TASK");
			task.setUser(user);
			tasks = taskService.insertTaskReturnAllTasks(task);
		}
		request.getSession().setAttribute("tasks", tasks);
		model.addAttribute("task", new Task());
		model.addAttribute("editTask", new Task());
		return "home";
	}
	
	@GetMapping("/edit")
	public String editTask(HttpServletRequest request, @ModelAttribute("task") Task task, Model model, @RequestParam("taskId") Long taskId) {
		logger.info("taskID: " + taskId);
		model.addAttribute("task", new Task());
		model.addAttribute("editTask", taskService.findTaskById(taskId));
		return "home";
	}

	@PostMapping("/updateTask")
	public String updateTask(@Valid @ModelAttribute("editTask") Task task, BindingResult result, 
						  Model model, HttpServletRequest req) {
		User user = (User) req.getSession().getAttribute("user");
		List<Task> tasks;
		if(result.hasErrors()) { 
			logger.info("INVALID TASK");
			tasks = user.getTasks();
		}
		else { 
			logger.info("VALID TASK");
			task.setUser(user);
			tasks = taskService.insertTaskReturnAllTasks(task);
		}
		req.getSession().setAttribute("tasks", tasks);
		model.addAttribute("task", new Task());
		model.addAttribute("editTask", new Task());
		return "home";
	}

	@PostMapping("/deleteTask")
	public String deleteTask( Model model, HttpServletRequest req, @RequestParam("taskId") Long taskId) {
		@SuppressWarnings("unchecked")
		List<Task> tasks = (List<Task>) req.getSession().getAttribute("tasks");
		Task task = taskService.findTaskById(taskId);
		
		//note: for some reason, if there is only 1 task in the list using removeIf does not work properly.
		//Hence we need to check for that condition and use removeAll instead.
		if(tasks.size() == 1) tasks.removeAll(tasks);
		else tasks.removeIf(t -> t.getId() == task.getId());
		
		taskService.deleteTask(task);
		logger.info("tasks: " + tasks.toString());
		
		req.getSession().setAttribute("tasks", tasks);
		model.addAttribute("task", new Task());
		model.addAttribute("editTask", new Task());
		return "home";
	}
}
