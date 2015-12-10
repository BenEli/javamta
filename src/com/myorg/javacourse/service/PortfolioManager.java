package com.myorg.javacourse.service;

import java.util.Date;

import com.myorg.javacourse.*;
import com.myorg.javacourse.model.Portfolio;
import com.myorg.javacourse.model.Stock;
/**
 * manages or create portfolio
 * @author ben and eli
 *
 */

public class PortfolioManager {
	
	Date date;
	/**
	 * create a new portfolio, for now with information entered in advance
	 * @return the new portfolio that was created
	 */
	public Portfolio getPortfolio(){
		
		date = new Date();
		boolean res;
		
		Portfolio portfolio = new Portfolio("portfolio1", null, (short)0);
		
		date.setDate(15);
		date.setMonth(11);
		date.setYear(2014);
		
		res = portfolio.addStock(new Stock("PIH", 13.1f, 12.4f, this.date, 1, -1));
		res = portfolio.addStock(new Stock("AAL", 5.78f, 5.5f, this.date, 1, -1));
		res = portfolio.addStock(new Stock("CAAS", 32.2f, 31.5f, this.date, 1, -1));
		
		return portfolio;
		
	}
	/**
	 * get specific stock from portfolio
	 * @param portfolio is the portfolio that hold the stock to return
	 * @param stockIndex is the index of the stock to return
	 * @return the wanted stock
	 */
	public Stock getStock(Portfolio portfolio, int stockIndex){
		return portfolio.getStock(portfolio.getPortfolioSize() - 1);
	}
	/**
	 * update the bid value in specific stock
	 * @param portfolio is the portfolio that hold the stock to update
	 * @param stockIndex is the index of the wanted stock
	 * @param newBid is the new value to put in bid
	 */
	public void changeStockBid(Portfolio portfolio, int stockIndex, float newBid){
		getStock(portfolio, stockIndex).setBid(newBid);
	}
	/**
	 * update the ask value in specific stock
	 * @param portfolio is the portfolio that hold the stock to update
	 * @param stockIndex is the index of the wanted stock
	 * @param newAsk is the new value to put in ask
	 */
	public void changeStockAsk(Portfolio portfolio, int stockIndex, float newAsk){
		getStock(portfolio, stockIndex).setAsk(newAsk);
	}

}