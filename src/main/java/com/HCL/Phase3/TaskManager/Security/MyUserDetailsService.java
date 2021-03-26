package com.HCL.Phase3.TaskManager.Security;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.HCL.Phase3.TaskManager.User.UserService;

@Service
public class MyUserDetailsService implements UserDetailsService{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired private UserService service;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<com.HCL.Phase3.TaskManager.User.User> user = service.getUser(username);
		logger.info("authenticating");
		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
		
		return new MyUserDetails(user.get());
	}

}
