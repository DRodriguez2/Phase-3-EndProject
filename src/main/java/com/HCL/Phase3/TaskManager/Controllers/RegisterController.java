package com.HCL.Phase3.TaskManager.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.HCL.Phase3.TaskManager.Exceptions.PasswordDoesNotMatchException;
import com.HCL.Phase3.TaskManager.Exceptions.UserAlreadyExistsException;
import com.HCL.Phase3.TaskManager.User.User;
import com.HCL.Phase3.TaskManager.User.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private UserService userService;

	@GetMapping() 
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping() 
	public ModelAndView registerUser(@Valid @ModelAttribute("user") User user, BindingResult check, @RequestParam("verify") String verify) {
		ModelAndView mv = new ModelAndView();
		String result = "";
		if(check.hasErrors()) { 
			mv.setViewName("register");
		}
		else {
			try {
				userService.registerUser(user, verify);
				mv.setViewName("loginPage");
				result = "<SPAN style='color:#50F227'>User registration successful</SPAN>";
			} catch(UserAlreadyExistsException | PasswordDoesNotMatchException e) {
				mv.setViewName("register");
				result = (e instanceof UserAlreadyExistsException) ? "<SPAN style='color:#F8DE57'>User already exists</SPAN>":"<SPAN style='color:#F8DE57'>Passwords do not match</SPAN>";
			}
		}
		mv.addObject("result", result);
		return mv;
	}
}
