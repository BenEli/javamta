package com.myorg.javacourse.model;
 
import org.algo.exception.PortfolioException;
import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;
 


import com.myorg.javacourse.*;
import com.myorg.javacourse.exception.*;
/**
 * Represents a stock portfolio
 * include the next information about the portfolio: name, number of stocks, the stocks in the portfolio
 * @author ben and eli
 *
 */
public class Portfolio  implements PortfolioInterface {
    /**
	 * 
	 */
	
	private final static int MAX_PORTFOLIO_SIZE = 5;
    private String title;
    private StockInterface[] stocks;
    private short portfolioSize;
    private float balance;
    /**
     * copy c'tor
     * @param newPortfolio is the portfolio that the c'tor copy
     */
    public Portfolio(Portfolio newPortfolio){
        this(newPortfolio.title, newPortfolio.stocks, newPortfolio.portfolioSize, newPortfolio.balance);
    }
    /**
     * c'tor
     */
    public Portfolio(){
        this(null , null, (short)0, (float)0);
    }
    /**
     * c'tor
     */
    public Portfolio(StockInterface[] stocks){
        this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
        for(int i = 0; i < stocks.length; i++)
            this.stocks[i] = stocks[i];
    }  
    /**
     * c'tor
     */
    public Portfolio(String title, StockInterface[] stocks, short portfolioSize, float balance){
       
        this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
        this.title = title;
        this.balance = balance;
       
        try{
            for(int i = 0; i < portfolioSize; i++){
                addStock((Stock)stocks[i]);
            }
        }catch(PortfolioException e){System.out.println("Portfolio constructor");}
       
        this.portfolioSize = portfolioSize;
    }
   
    public void setTitle(String title){
        this.title = title;
    }
   
    public void setPortfolioSize(int portfolioSize){
        this.portfolioSize = (short) portfolioSize;
    }
   
    public String getTitle(){
        return this.title;
    }
   
    public Stock[] getStocks(){
        return (Stock[])this.stocks;
    }
   
    public int getPortfolioSize(){
        return portfolioSize;
    }
    /**
     * calculate the value of all the stocks in the portfolio
     * @return the value of all the stocks in the portfolio
     */
    public float getStockValue(){
        float res = 0;
       
        for(int i = 0; i < portfolioSize; i++)
            res += (stocks[i].getBid() * ((Stock)stocks[i]).getStockQuantity());
       
        return res;
    }
   
    public float getBalance(){
        return this.balance;
    }
   
    public static Object getMaxSize() {
        return MAX_PORTFOLIO_SIZE;
    }
   
    /**
     * calculate the value of the portfolio including stocks value and balance
     * @return the value of the portfolio
     */
    public float getTotalValue(){
        return getBalance() + getStockValue();
    }
   
    /**
     * get the wanted stock
     * @param symbol is the symbol of the wanted stock
     * @return the wanted stock if the stock dose exist or return null
     */
    public Stock findStock(String symbol){
        StockInterface res = null;
       
        for(int i = 0; i < portfolioSize && res == null; i++){
            if(symbol.equals(stocks[i].getSymbol()))
                res = stocks[i];
        }
        return (Stock)res;
    }
   
    /**
     * get the wanted stock
     * @param symbol the symbol of the wanted stock
     * @return the wanted stock or -1 if stock dose not exist in stocks
     */
    private int getStock(String symbol){
        int res = -1;
        int i = 0;
       
        while(i < portfolioSize && res == -1)
        {
            if(symbol.equals(stocks[i].getSymbol()))
                res = i;
           
            i++;
        }          
        return res;
    } 
    /**
     * 
     * update portfolio balance if possible
     * @param amount the amount of money to add or sub from balance (negative number to sub)
     * @throws BalanceException
     */   
    public void updateBalance(float amount) throws BalanceException{
    	
        if(this.balance + amount >= 0)
			  this.balance += amount;
        else
        	throw new BalanceException("Attempt to enter negative account balance!");
    }
   
    /**
     * 
     * if possible and the stock is not already in stocks add stock to portfolio
     * @param newStock the stock to add
     * @throws PortfolioException
     */
    public void addStock(Stock newStock) throws PortfolioException{
        if(portfolioSize < MAX_PORTFOLIO_SIZE)
        {
            if(getStock(newStock.getSymbol()) == -1)
            {
                newStock.setStockQuantity(0);
                this.stocks[portfolioSize++] = new Stock(newStock);
            }
            else
                throw new StockAlreadyExistsException();
        }
        else
            throw new PortfolioFullException("cant add new stock, portfolio can have only " + MAX_PORTFOLIO_SIZE + " stocks ");  
    }
    /**
     * if possible sell all stock from the wanted stock, remove from portfolio and Rearranges the stock array
     * @param symbol is the symbol of the stock to delete
     * @throws PortfolioException
     */
    public void removeStock(String symbol) throws PortfolioException  {
        int removeIndex = getStock(symbol);
       
        if(removeIndex != -1)
        {
            if(((Stock)stocks[removeIndex]).getStockQuantity() != 0)
                sellStock(symbol, ((Stock)stocks[removeIndex]).getStockQuantity());
           
            for(int i = removeIndex; i < portfolioSize - 1; i++)
                stocks[i] = stocks[i+1];
           
            stocks[--portfolioSize] = null;
        }
        else
            throw new StockNotExistException("cant remove stock becouse stock is not in portfolio");
       
    }
    /**
     * sell if possible sell the wanted quantity from wanted stock
     * @param symbol is the symbol of wanted stock
     * @param quantity is the quantity of stocks to sell (-1) for sell all
     * @throws BalanceException 
     * @throws PortfolioException 
     */
    public void sellStock(String symbol, int quantity) throws CouldNotSellStockException, BalanceException {
        int sellIndex = getStock(symbol);
        boolean res = false;
       
        if(sellIndex != -1)
        {
            if(quantity == -1)
            {
                quantity = ((Stock)stocks[sellIndex]).getStockQuantity();
                res = true;
            }
            else if(quantity > 0 && quantity <= ((Stock)stocks[sellIndex]).getStockQuantity())
                res = true;
        }
       
        if(res)
        {
            updateBalance(stocks[sellIndex].getBid() * quantity);
            ((Stock)stocks[sellIndex]).updateQuantity(-quantity);
        }
        else
            throw new CouldNotSellStockException();
    }
   
    /**
     * if possible buy wanted quantity of wanted stock
     * if stock dose not exist in stocks add this stock to portfolio
     * @param stock is the stock to buy
     * @param quantity is the quantity of stocks to buy (-1 for buy all stocks you can)
     * @return true for successful buy or false if not
     * throws PortfolioException
     * @throws BalanceException 
     */
    public void buyStock(Stock stock, int quantity) throws CouldNotBuyStockException, BalanceException {
        int quantityToBuy = 0, buyIndex;
        boolean res = false;
       
        buyIndex = getStock(stock.getSymbol());
       
        if(buyIndex == -1)
        {
           try{
        	   addStock(stock);
        	   buyIndex = getStock(stock.getSymbol());  
           }
           catch(PortfolioException e){
        	   throw new CouldNotBuyStockException("Couldn't buy stock because , " + e.getMessage() );
           }
            
        }     
        if(quantity == -1)
        {
            while((quantityToBuy + 1) * stock.getAsk() <= balance )
                quantityToBuy++;
           
            res = true;
        }
        else if(quantity >= 0 && (quantity * stock.getAsk() <= balance))
        {
            quantityToBuy = quantity;
            res = true;
        }
       
        if(res && buyIndex != -1)
        {
            updateBalance(-1*(quantityToBuy * stock.getAsk()));
            ((Stock)stocks[buyIndex]).updateQuantity(quantityToBuy);
        }
        else
            throw new CouldNotBuyStockException();
    }
    /**
     * get the information about portfolio
     * @return the next information about the portfolio as html string: name, portfolio value, total value of stocks, balance
     * and details about the stocks in the portfolio
     */
    public String getHtmlString(){
        String tempString = "<h1>" +this.title+ "</h1>" + "<p><b>Portfolio value</b> :" + getTotalValue() +
                "<b> Total Stock value</b> :" + getStockValue() + "<b> Balance</b> :" + getBalance() + "</p>";
       
        for(short i = 0; i < this.portfolioSize; i++)
            tempString = tempString.concat("<p>" +((Stock)this.stocks[i]).getHtmlDescription()+ "</p>");
       
        return tempString;
    }
}