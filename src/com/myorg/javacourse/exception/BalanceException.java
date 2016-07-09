package com.myorg.javacourse.exception;

import org.algo.exception.PortfolioException ;

/**
 * Balance exception extends general PortfolioException throws when attemp to enter negetive account balance
 * @author Ben and Eli
 * 
 *
 */
public class BalanceException  extends PortfolioException { 
	


	private static final long serialVersionUID = 1L;

	public BalanceException() {
		super("Attempt to enter negative account balance!");
	}	
	
	public BalanceException(String  message){
		super(message);
	}
}
