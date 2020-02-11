package com.epam.exception;

public class NotAbleToSaveException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NotAbleToSaveException(String message)
	{
		super(message);
	}

}
