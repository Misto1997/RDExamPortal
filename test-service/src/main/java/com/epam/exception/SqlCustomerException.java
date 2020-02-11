package com.epam.exception;

public class SqlCustomerException extends Exception{

	private static final long serialVersionUID = 1L;

	public SqlCustomerException(String message)
	{
		super(message);
	}
}
