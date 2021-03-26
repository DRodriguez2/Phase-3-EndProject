package com.HCL.Phase3.TaskManager.Exceptions;

public class PasswordDoesNotMatchException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordDoesNotMatchException() {
		super("PASSWORDS DO NOT MATCH");
	}
}
