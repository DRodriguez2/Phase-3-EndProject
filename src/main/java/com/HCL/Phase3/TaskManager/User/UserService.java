package com.HCL.Phase3.TaskManager.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.HCL.Phase3.TaskManager.Exceptions.PasswordDoesNotMatchException;
import com.HCL.Phase3.TaskManager.Exceptions.UserAlreadyExistsException;


@Service
public class UserService {

	@Autowired private UserRepository userRepository;
	@Autowired private PasswordEncoder encoder;
	
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public Optional<User> getUser(@PathVariable String username) {
		return userRepository.findById(username);
	}
	
	public boolean registerUser(User user, String validate) throws UserAlreadyExistsException, PasswordDoesNotMatchException {
		if (getUser(user.getUsername()).isPresent()) throw new UserAlreadyExistsException();
		else if (!(user.getPassword().equals(validate))) throw new PasswordDoesNotMatchException();
		else {
			user.setPassword(encoder.encode(validate));
			userRepository.save(user);
			return true;
		}
	}
	
	public void insertUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
	}

}
