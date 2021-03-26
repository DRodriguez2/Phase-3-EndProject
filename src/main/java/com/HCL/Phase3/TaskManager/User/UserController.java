package com.HCL.Phase3.TaskManager.User;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.HCL.Phase3.TaskManager.Task.Task;
import com.HCL.Phase3.TaskManager.Task.TaskService;

@Controller
public class UserController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	@Autowired
	private TaskService taskService;

	@GetMapping("/")
	public String redirectLogin() {
		return "redirect:/home";
	}

	@GetMapping("/loginPage")
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		logger.info("request recieved in controller");

		String result = "";
		if (error != null)
			result = "<SPAN style='color:#F62020'>Invalid Login</SPAN>";
		else if (logout != null)
			result = "<SPAN style='color:#50F227'>Logout Successful</SPAN>";

		ModelAndView mv = new ModelAndView();
		mv.addObject("result", result);
		mv.setViewName("loginPage");
		return mv;
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/loginPage?logout=true";
	}	

	@GetMapping("/home")
	public String home(Authentication authentication, Model model, HttpServletRequest request) {
//		MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
//		HttpSession session = request.getSession();
//		User user = (User)session.getAttribute("user");
//		logger.info("logged in with: " + user.getUsername());
//		model.addAttribute("user", user);
//		model.addAttribute("tasks", user.getTasks());
		model.addAttribute("task", new Task());
		model.addAttribute("editTask", new Task());
		return "home";
	}
	
	@PostMapping("/home/addTask")
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
			tasks = taskService.insertTask(task);
		}
		request.getSession().setAttribute("tasks", tasks);
		model.addAttribute("task", new Task());
		model.addAttribute("editTask", new Task());
		return "home";
	}
	
	@GetMapping("/home/edit")
	public String editTask(HttpServletRequest request, @ModelAttribute("task") Task task, Model model, @RequestParam("taskId") Long taskId) {
		logger.info("taskID: " + taskId);
		model.addAttribute("task", new Task());
		model.addAttribute("editTask", taskService.findTaskById(taskId));
		return "home";
	}

	@PostMapping("/home/updateTask")
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
			tasks = taskService.insertTask(task);
		}
		req.getSession().setAttribute("tasks", tasks);
		model.addAttribute("task", new Task());
		model.addAttribute("editTask", new Task());
		return "home";
	}

	@PostMapping("/home/deleteTask")
	public String deleteTask( Model model, HttpServletRequest req, @RequestParam("taskId") Long taskId) {
		List<Task> tasks = (List<Task>) req.getSession().getAttribute("tasks");
		Task task = taskService.findTaskById(taskId);
		tasks.removeIf(t -> t.getId() == task.getId());
		taskService.deleteTask(task);
		logger.info("tasks: " + tasks.toString());
		
		req.getSession().setAttribute("tasks", tasks);
		model.addAttribute("task", new Task());
		model.addAttribute("editTask", new Task());
		return "home";
	}

	@GetMapping("/user/all")
	public Iterable<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/user/{username}")
	public Optional<User> getUser(@PathVariable String username) {
		return userService.getUser(username);
	}

	@GetMapping("/user/{username}/tasks")
	public List<Task> getAllTasksByUsername(@PathVariable String username) {
		return taskService.getAllTasksByUsername(username);
	}

}
