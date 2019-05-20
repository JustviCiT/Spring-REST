package com.rest.error;

public class OrderNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3547128340681704995L;

	public OrderNotFoundException (Long id) {
		super("Ordine non trovato : "+id);
	}
}
