package com.myorg.javacourse.model;

import com.myorg.javacourse.*;
/**
 * Represents a stock portfolio
 * include the next information about the portfolio: name, number of stocks, the stocks in the portfolio 
 * @author ben and eli
 *
 */
public class Portfolio {
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	private Stock[] stocks;
	private short portfolioSize;
	
	/**
	 * copy c'tor
	 * @param newPortfolio is the portfolio that the c'tor copy
	 */
	public Portfolio(Portfolio newPortfolio){
		this(newPortfolio.title, newPortfolio.stocks, newPortfolio.portfolioSize);
	}
	/**
	 * c'tor
	 */
	public Portfolio(String title, Stock[] stocks, short portfolioSize){
		
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.setTitle(title);
		
		for(int i = 0; i < portfolioSize; i++)
			addStock(new Stock(stocks[i]));
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public Stock[] getStocks(){
		return this.stocks;
	}
	
	public int getPortfolioSize(){
		return portfolioSize;
	}
	/**
	 * get the wanted stock
	 * @param stockIndex the index of the wanted stock
	 * @return the wanted stock
	 * check if the stock in wanted index dose exist 
	 */
	public Stock getStock(int stockIndex){
		if(stockIndex < portfolioSize)
			return stocks[stockIndex];
		else
			return null;
	}
	/**
	 * if possible add stock to portfolio
	 * @param newStock the stock to add
	 * @return true if succeed else false
	 */
	public boolean addStock(Stock newStock){
		if(portfolioSize < MAX_PORTFOLIO_SIZE)
		{
			this.stocks[portfolioSize++] = newStock;
			return true;
		}
		else
			return false;
	}
	/**
	 * if possible remove the wanted stock from portfolio and Rearranges the stock array
	 * @param removeIndex is the index of the stock to delete
	 * @return true if the removal succeed else false
	 */
	public boolean removeStock(int removeIndex)
	{
		if(removeIndex < portfolioSize)
		{
			stocks[removeIndex] = null;
			
			for(int i = removeIndex; i < portfolioSize - 1; i++)
				stocks[i] = stocks[i+1];
			
			portfolioSize--;
			
			return true;
		}
		else
			return false;
		
	}
	/**
	 * get the information about portfolio
	 * @return the next information about the portfolio as html string: name, details about the stocks in the portfolio 
	 */
	public String getHtmlString(){
		String tempString = "<h1>" +this.title+ "</h1>";
		for(short i = 0; i < this.portfolioSize; i++)
			tempString = tempString.concat("<p>" +this.stocks[i].getHtmlDescription()+ "</p>");
		return tempString; 
	}
}