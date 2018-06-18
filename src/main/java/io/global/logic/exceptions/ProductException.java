package io.global.logic.exceptions;

public class ProductException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8438324940743327200L;

	public ProductException(String produtId) {
		super("Error while processing your request at id" + produtId);
	}
}
