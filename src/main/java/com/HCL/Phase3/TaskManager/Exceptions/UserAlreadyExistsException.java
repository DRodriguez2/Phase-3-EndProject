package com.HCL.Phase3.TaskManager.Exceptions;

public class UserAlreadyExistsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1063046616060932674L;

	public UserAlreadyExistsException() {
		super("USER ALREADY EXISTS");
	}
}
