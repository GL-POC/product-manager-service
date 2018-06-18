package io.global.logic.exceptions;

public class ProductNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3149261560508940332L;
	
	public ProductNotFoundException(String productId) {
		super("Product Not Found for id" + productId);
	}

}
