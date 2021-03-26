package com.HCL.Phase3.TaskManager.Controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
}
