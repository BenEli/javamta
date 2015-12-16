package com.myorg.javacourse.model;

import com.myorg.javacourse.*;
/**
 * Represents a stock portfolio
 * include the next information about the portfolio: name, number of stocks, the stocks in the portfolio ,the balance of portfolio
 * @author ben and eli
 *
 */
public class Portfolio {
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	private Stock[] stocks;
	private short portfolioSize;
	private float balance ;
	
	/**
	 * copy c'tor
	 * @param newPortfolio is the portfolio that the c'tor copy
	 */
	public Portfolio(Portfolio newPortfolio){
		this(newPortfolio.title, newPortfolio.stocks, newPortfolio.portfolioSize,newPortfolio.balance);
	}
	/**
	 * c'tor
	 */
	public Portfolio(String title, Stock[] stocks, short portfolioSize,float balance){
		
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.setTitle(title);
		
		for(int i = 0; i < portfolioSize; i++)
			addStock(stocks[i]);
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
	 * update the balance of portfolio
	 * check if it is avalible to update the new amount to the balance
	 * @param amount the add amount
	 * print if not possible to add
	 */
	public void updateBalance(float amount ){
		
	if ( this.balance + amount >= 0  )
		this.balance += amount ;
	else
		System.out.println("This amount is not avalible ont your balance!");
	}
	/**
	 * check if symbol exist already in the portfolio or not 
	 * @param symbol the symbol to check
	 * @return the index of the stock if exist
	 * return -1 if symbol not exist
	 */
	private int checkExistence(String symbol){
		for (int res = 0; res < portfolioSize; res++) {
			if(stocks[res].getSymbol().equals(symbol) )
				return res;
		}
		return -1;
	}
	/**
	 * if possible add stock to portfolio
	 * if  stock not exist creayte new stock
	 * @param newStock the stock to add
	 * @print if succed 
	 */
	public void addStock(Stock newStock){
		if(portfolioSize < MAX_PORTFOLIO_SIZE && checkExistence(newStock.getSymbol() ) == -1 )
		{
			this.stocks[portfolioSize++] = new Stock(newStock);
			stocks[portfolioSize -1 ].setStockQuantity(0);
		}
		else if (portfolioSize == MAX_PORTFOLIO_SIZE )
			System.out.println("Can’t add new stock, portfolio can have only  " + MAX_PORTFOLIO_SIZE + "  stocks.");
		else
			System.out.println("This stock is already exists.");
	}
	/**
	 * 
	 * @param symbol is the symbol of the stock to sell
	 * @param quantity is the quantity to sell 
	 * @return
	 */
	public boolean sellStock(String symbol , int quantity){
		
		int sellIndex = checkExistence(symbol);
		
		if(quantity == -1 && sellIndex > 0 ){
			updateBalance(stocks[sellIndex].getStockQuantity() * stocks[sellIndex].getBid() );
			stocks[sellIndex].setStockQuantity(0);
			return true ;
		}
		else if(sellIndex > 0 && quantity > 0 &&  quantity <= stocks[sellIndex].getStockQuantity() ){
			updateBalance(quantity * stocks[sellIndex].getBid() );
			stocks[sellIndex].updateStockQuantity(-quantity);
			return true ;
		}
		else
			return false ; 
	}
	/**
	 * buy new stock if there is enough money in balance to buy new stock
	 * @param stock get the stock details to buy 
	 * @param quantity get the amount of stocks to buy 
	 * @param quantityToBuy is the index that save the amount off stocks to buy 
	 * buyIndex check's if the stock is already exists in the portfolio 
	 * 
	 * @@return true if the removal succeed else false
	 */
	public boolean buyStock(Stock stock , int quantity ){
		int  quantityToBuy = 0;
		int  buyIndex = checkExistence(stock.getSymbol());
		
		while(quantityToBuy * stock.getAsk() <= this.balance)
			quantityToBuy++ ;
			
		if(quantity == -1  && buyIndex > 0 ){
			stocks[buyIndex].updateStockQuantity(quantityToBuy);
			updateBalance( -(quantityToBuy * stocks[buyIndex].getAsk() ) );
			return true ;
		}
		else if (buyIndex > 0 && quantity > 0 && ( quantity * stock.getAsk() ) <= this.balance  ){
			stocks[buyIndex].updateStockQuantity(quantity);
			updateBalance( -(quantity * stocks[buyIndex].getAsk() ) );
			
			return true ;
		}
		else if (buyIndex < 0  &&  quantity  == -1 && portfolioSize < MAX_PORTFOLIO_SIZE ){
			addStock(stock);
			stocks[portfolioSize-1].updateStockQuantity(quantityToBuy);
			updateBalance(-(quantityToBuy * stocks[portfolioSize-1].getAsk() ) );
			return true ;
		}
		else if (buyIndex < 0  &&  quantity > 0  && ( quantity * stock.getAsk() ) <= this.balance && portfolioSize < MAX_PORTFOLIO_SIZE ){
			addStock(stock);
			stocks[portfolioSize-1].updateStockQuantity(quantity);
			updateBalance(-(quantity * stocks[portfolioSize-1].getAsk() ) );
			return true ;
		}
		else{
			System.out.println("Not enough balance to complete purchase");
			return false ;
		}
	}
	/**
	 * if possible remove the wanted stock from portfolio and Rearranges the stock array
	 * @param removeIndex is the index of the stock to delete
	 * @return true if the removal succeed else false
	 */
	public boolean removeStock(String symbol)
	{
		int removeIndex = checkExistence(symbol);
		if( removeIndex  > 0 )
		{
			sellStock(symbol , stocks[removeIndex].getStockQuantity() );
			stocks[removeIndex] = null ;
			
			for(int i = removeIndex; i < portfolioSize - 1; i++)
				stocks[i] = stocks[i+1];
			portfolioSize--;
			
			return true;
		}
		else
			return false;
		
	}
	public float getStocksValue(){
		float stocksValue = 0 ; 
		for (int i = 0; i < portfolioSize; i++) 
			stocksValue += ( stocks[i].getBid() * stocks[i].getStockQuantity() ) ; 
		return stocksValue ;
	}
	
	public float getBalance(){
		return this.balance;
	}
	
	public float getTotalValue(){
		return ( getBalance() + getStocksValue() );
	}
	
	
	
	
	/**
	 * get the information about portfolio
	 * @return the next information about the portfolio as html string: name, details about the stocks in the portfolio 
	 */
	public String getHtmlString(){
		String tempString = "<h1>" +this.title+ "</h1>" + "<p>" + "<b>Portfolio value</b> : " + getTotalValue() + "$ " + "<b>Portfolio balance</b> : " + getBalance() +"$ " + "<b>Portfolio stocks value</b> : "  + getStocksValue() + "$ " +"</p>";
		for(short i = 0; i < this.portfolioSize; i++)
			tempString = tempString.concat("<p>" +this.stocks[i].getHtmlDescription()+ "</p>");
		return tempString; 
	}
}