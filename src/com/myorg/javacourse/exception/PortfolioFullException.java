package com.myorg.javacourse.exception;

import org.algo.exception.PortfolioException;


/**
 * PortfolioFullException exception extends general PortfolioException throws when Portfolio is full
 * @author Ben and Eli
 * 
 */
public class PortfolioFullException extends PortfolioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PortfolioFullException() {
		super("Portfolio is full ! ");	
	}
	
	public PortfolioFullException(String message){
		super(message);
	}

}
