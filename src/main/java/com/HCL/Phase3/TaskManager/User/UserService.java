package com.HCL.Phase3.TaskManager.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


@Service
public class UserService {

	@Autowired private UserRepository userRepository;
	
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public Optional<User> getUser(@PathVariable String username) {
		return userRepository.findById(username);
	}
	
	/**
	 * Returns 1 if user does not exist, 2 if password is invalid, and 3 if login is valid. 
	 * @param username
	 * @param password
	 * @return
	 */
	public int authenticate(String username, String password) {
		Optional<User> user;
		if((user = userRepository.findById(username)).isPresent()) {
			return (password.equals(user.get().getPassword())) ? 3:2;
		}
		else return 1;
	}
}
