package com.xapo.exception;

public class XapoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public XapoException(String message){
		super(message);
	}
	
	public XapoException(Throwable th){
		super(th);
	}

}
