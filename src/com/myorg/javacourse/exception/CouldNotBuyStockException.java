package com.myorg.javacourse.exception;

import org.algo.exception.PortfolioException;


/**
 * couldNotSellStockException extends general PortfolioException when not possible to sell stock any reason 
 * @author Ben and Eli 
 *
 */
public class CouldNotBuyStockException extends PortfolioException  {
	
	private static final long serialVersionUID = 1L;

	public CouldNotBuyStockException() {
		super("could not buy stock");
	}	
		
	public CouldNotBuyStockException(String  message){
		super(message);
	}
}