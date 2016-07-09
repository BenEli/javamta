package com.myorg.javacourse.exception;

import org.algo.exception.PortfolioException;

/**
 * StockAlreadyExistsException exception extends general PortfolioException throws when stock is already excist in portfolio
 * @author Ben and Eli
 * 
 */
public class StockAlreadyExistsException extends PortfolioException {
		
		private static final long serialVersionUID = 1L;
		
		public StockAlreadyExistsException() {
			super("Stock is already excist in the portfolio ! ");	
		}
		
		public StockAlreadyExistsException(String message){
			super(message);
		}
}
