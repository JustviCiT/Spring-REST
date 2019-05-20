package com.rest.error;

public class ClientNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 253228653660248293L;

	public ClientNotFoundException(Long id) {
		super("Cliente non trovato : "+ id);
	}

}
