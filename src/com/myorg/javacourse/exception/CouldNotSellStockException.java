package com.myorg.javacourse.exception;

import org.algo.exception.PortfolioException;


/**
 * couldNotSellStockException extends general PortfolioException when not possible to buy stock any reason 
 * @author Ben and Eli 
 *
 */
public class CouldNotSellStockException extends PortfolioException  {
	
	private static final long serialVersionUID = 1L;

	public CouldNotSellStockException() {
		super("could not sell stock");
	}	
		
	public CouldNotSellStockException(String  message){
		super(message);
	}
}


