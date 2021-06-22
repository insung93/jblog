package com.douzone.jblog.exception;

public class GuestbookRepositoryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GuestbookRepositoryException() {
		super("GuestbookPepositoryException Occurs");
	}
	public GuestbookRepositoryException(String message ) {
		super(message);
	}
}
