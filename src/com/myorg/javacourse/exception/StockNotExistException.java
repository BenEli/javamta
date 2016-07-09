package com.myorg.javacourse.exception;

import org.algo.exception.PortfolioException;

/**
 * StockAlreadyExistsException exception extends general PortfolioException throws when stock is not excist in nasdaq 
 * @author Ben and Eli
 * 
 */
public class StockNotExistException extends PortfolioException {

	private static final long serialVersionUID = 1L;
	
	public StockNotExistException() {
		super("Stock is  not excist on nasdaq ! ");	
	}
	
	public StockNotExistException(String message){
		super(message);
	}
}
