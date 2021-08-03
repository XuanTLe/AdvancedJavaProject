package com.cognixia.jump.finalProject;

 // -- Inheritance and Custom Exception --
public class MinimumWageException extends Exception {
	
	private static final long serialVersionUID = -519098988822619554L;

	public MinimumWageException(int salary) {
		super("\n$" + salary + " is below the minimum wage ($14400)!!");
	}
}
