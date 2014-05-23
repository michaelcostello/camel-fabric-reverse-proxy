package com.redhat.httpgateway.exception;

public class BindingException extends Exception {

	
	public BindingException(String message) {
		super(message);
		
	}

	public BindingException(Throwable cause) {
		super(cause);
		
	}

	public BindingException(String message, Throwable cause) {
		super(message, cause);
		
	}

}
