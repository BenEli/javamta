package com.myorg.javacourse.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.algo.dto.PortfolioDto;
import org.algo.dto.PortfolioTotalStatus;
import org.algo.dto.StockDto;
import org.algo.exception.PortfolioException;
import org.algo.exception.SymbolNotFoundInNasdaq;
import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;
import org.algo.service.DatastoreService;
import org.algo.service.MarketService;
import org.algo.service.PortfolioManagerInterface;
import org.algo.service.ServiceManager;

 
import com.myorg.javacourse.model.Portfolio;
import com.myorg.javacourse.model.Stock;
import com.myorg.javacourse.model.Stock.ALGO_RECOMMENDATION;
import com.myorg.javacourse.exception.*;
/**
 * manages or create portfolio
 * @author ben and eli
 *
 */
 
public class PortfolioManager  implements PortfolioManagerInterface {
   
    /**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	private DatastoreService datastoreService = ServiceManager.datastoreService();
 
    /**
     * Returns portfolio from database
     * @return portfolio Portfolio from dto*/
    public PortfolioInterface getPortfolio() {
        PortfolioDto portfolioDto = datastoreService.getPortfolilo();
        return fromDto(portfolioDto);
    }
 
    /**
     * Update portfolio with stocks
     */
    @Override
    public void update() {
        StockInterface[] stocks = getPortfolio().getStocks();
        List<String> symbols = new ArrayList<>((int)Portfolio.getMaxSize());
        for (StockInterface si : stocks) {
            symbols.add(si.getSymbol());
        }
 
        List<Stock> update = new ArrayList<>((int)Portfolio.getMaxSize());
        List<Stock> currentStocksList = new ArrayList<Stock>();
        try {
            List<StockDto> stocksList = MarketService.getInstance().getStocks(symbols);
            for (StockDto stockDto : stocksList) {
                Stock stock = fromDto(stockDto);
                currentStocksList.add(stock);
            }
 
            for (Stock stock : currentStocksList) {
                update.add(new Stock(stock));
            }
 
            datastoreService.saveToDataStore(toDtoList(update));
 
        } catch (SymbolNotFoundInNasdaq e) {
            System.out.println(e.getMessage());
        }
    }
 
    /**
     * get portfolio total status
     * @return Returns portfolio status from DB
     */
    public PortfolioTotalStatus[] getPortfolioTotalStatus () {
 
        Portfolio portfolio = (Portfolio) getPortfolio();
        Map<Date, Float> map = new HashMap<>();
 
        //get stock status from db.
        StockInterface[] stocks = (StockInterface[])portfolio.getStocks();
        for (int i = 0; i < stocks.length; i++) {
            StockInterface stock = stocks[i];
 
            if(stock != null) {
                List<StockDto> stockHistory = null;
                try {
                    stockHistory = datastoreService.getStockHistory(stock.getSymbol(),30);
                } catch (Exception e) {
                    return null;
                }
                for (StockDto stockDto : stockHistory) {
                    Stock stockStatus = fromDto(stockDto);
                    float value = stockStatus.getBid()*stockStatus.getStockQuantity();
 
                    Date date = stockStatus.getDate();
                    Float total = map.get(date);
                    if(total == null) {
                        total = value;
                    }else {
                        total += value;
                    }
 
                    map.put(date, value);
                }
            }
        }
 
        PortfolioTotalStatus[] ret = new PortfolioTotalStatus[map.size()];
 
        int index = 0;
        //create dto objects
        for (Date date : map.keySet()) {
            ret[index] = new PortfolioTotalStatus(date, map.get(date));
            index++;
        }
 
        //sort by date ascending.
        Arrays.sort(ret);
 
        return ret;
    }
 
    /**
     * if possible add stock to portfolio
     * @param symbol is the symbol of the stock to add
     */
    @Override
    public void addStock(String symbol) throws PortfolioException{
        Portfolio portfolio = (Portfolio) getPortfolio();
 
        try {
            //get current symbol values from nasdaq
            StockDto stockDto = ServiceManager.marketService().getStock(symbol);
            Stock stock = fromDto(stockDto);
           //NOT THROWING EXCEPTION IF NOT FOUNF , ADDING STOCK SYMBOL THAT DOES NOT EXCIST 
            //first thing, add it to portfolio.
            portfolio.addStock(stock);      
 
            //second thing, save the new stock to the database.
            datastoreService.saveStock(toDto(portfolio.findStock(symbol)));
           
            //save the portfolio in the database
            flush(portfolio);
        } catch (SymbolNotFoundInNasdaq e) {
            throw new PortfolioException("Stock Not Exists: "+symbol+" in Nasdaq");
        } catch (PortfolioException e ) {
        	throw e ;
        }
        
    }
 
    /**
     * update database with new portfolio's data
     * @param portfolio
     */
    private void flush(Portfolio portfolio) {
        datastoreService.updatePortfolio(toDto(portfolio));
    }
 
    /**
     * fromDto - get stock from Data Transfer Object
     * @param stockDto is the stock to copy as dto stock
     * @return Stock newstock is the copy of stockDto
     */
    private Stock fromDto(StockDto stockDto) {
       
        Stock newStock = new Stock();
 
        newStock.setSymbol(stockDto.getSymbol());
        newStock.setAsk(stockDto.getAsk());
        newStock.setBid(stockDto.getBid());
        newStock.setDate(stockDto.getDate());
        newStock.setStockQuantity(stockDto.getQuantity());
       
        if (stockDto.getRecommendation() != null)
            newStock.setRecommendation(ALGO_RECOMMENDATION.valueOf(stockDto.getRecommendation()));
        //newStock.setRecommendation(stockDto.getRecommendation());
        return newStock;
    }
 
    /**
     * toDto - covert Stock to Stock DTO
     * @param inStock the stock to convert
     * @return StockDto the copy of the stock as stockDto
     */
    private StockDto toDto(StockInterface inStock) {
        if (inStock == null){
            return null;
        }
       
        Stock stock = (Stock) inStock;
       
        if(stock.getRecommendation() == null){
            stock.setRecommendation(ALGO_RECOMMENDATION.NONE);
        }
        return new StockDto(stock.getSymbol(), stock.getAsk(), stock.getBid(),
                stock.getDate(), stock.getStockQuantity(), stock.getRecommendation().name());
    }
 
    /**
     * toDto - converts Portfolio to Portfolio DTO
     * @param portfolio is the portfolio to copy
     * @return PortfolioDto
     */
    private PortfolioDto toDto(Portfolio portfolio) {
        StockDto[] array = null;
        StockInterface[] stocks = portfolio.getStocks();
        if(stocks != null) {
            array = new StockDto[stocks.length];
            for (int i = 0; i < stocks.length; i++) {
                array[i] = toDto(stocks[i]);
            }
        }
        return new PortfolioDto(portfolio.getTitle(), portfolio.getBalance(), array);
    }
 
    /**
     * fromDto - converts portfolioDto to Portfolio
     * @param dto is the portfolioDto to copy
     * @return portfolio the copy of portfolio dto
     */
    private Portfolio fromDto(PortfolioDto dto) {
        StockDto[] stocks = dto.getStocks();
        Portfolio ret;
        if(stocks == null) {
            ret = new Portfolio();         
        }else {
            List<Stock> stockList = new ArrayList<Stock>();
            for (StockDto stockDto : stocks) {
                stockList.add(fromDto(stockDto));
            }
 
            Stock[] stockArray = stockList.toArray(new Stock[stockList.size()]);
            ret = new Portfolio(stockArray);
           
            int stockCounter = 0;
            for(StockInterface s : ret.getStocks()){
                if(s != null)
                    stockCounter++;
            }
            ret.setPortfolioSize(stockCounter);
        }
        ret.setTitle(dto.getTitle());
        try {
            ret.updateBalance(dto.getBalance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }  
 
    /**
     * toDtoList - convert List of Stocks to list of Stock DTO
     * @param stocks
     * @return stockDto
     */
    private List<StockDto> toDtoList(List<Stock> stocks) {
 
        List<StockDto> ret = new ArrayList<StockDto>();
 
        for (Stock stockStatus : stocks) {
            ret.add(toDto(stockStatus));
        }
        return ret;
    }
   
    /**
     * set the portfolio title and save it in the DB
     * @param title is the new title to set
     */
    @Override
    public void setTitle(String title) {
        Portfolio portfolio = (Portfolio) getPortfolio();
        portfolio.setTitle(title);
        flush(portfolio);  
    }
 
    /**
     * update portfolio balance if possible and save it in DB
     * @param amount the amount of money to add or sub from balance (negative number to sub)
     * @throws BalanceException 
     */
    public void updateBalance(float value) throws BalanceException{
        Portfolio portfolio = (Portfolio) getPortfolio();
        try {
			portfolio.updateBalance(value);
		} catch (BalanceException e) {
			throw e ;
			//e.printStackTrace();
		}
        flush(portfolio);
    }
 
    /**
     * if possible buy wanted quantity of wanted stock
     * @param symbol is the symbol of the stock to buy
     * @param quantity is the quantity of stocks to buy (-1 for buy all stocks you can)
     * @throws PortfolioException , BalanceException 
     */
    @Override
    public void buyStock(String symbol, int quantity) throws PortfolioException  {
        Portfolio portfolio = (Portfolio) getPortfolio();
        try {
			portfolio.buyStock(new Stock(portfolio.findStock(symbol)), quantity);
		} catch (PortfolioException e) {
			throw new PortfolioException(e.getMessage());
			// TODO Auto-generated catch block
		}      
        //save the portfolio in the database
        flush(portfolio);
    }
 
    /**
     *  if possible sell the wanted quantity from wanted stock
     * @param symbol is the symbol of wanted stock
     * @param quantity is the quantity of stocks to sell (-1) for sell all
     */
    @Override
    public void sellStock(String symbol, int quantity) throws PortfolioException  {
        Portfolio portfolio = (Portfolio) getPortfolio();
        try {
			portfolio.sellStock(symbol, quantity);
		} catch (PortfolioException e) {
			throw e ;
			//e.printStackTrace();
		}
        //save the portfolio in the database
        flush(portfolio);      
    }
 
    /**
     * remove stock from the portfolio
     * @param symbol is the symbol of the stock to delete
     */
    @Override
    public void removeStock(String symbol) throws PortfolioException {
        Portfolio portfolio = (Portfolio) getPortfolio();
        try {
			portfolio.removeStock(symbol);
		} catch (PortfolioException e) {
			throw e ;
			//e.printStackTrace();
		}      
        //save the portfolio in the database
        datastoreService.saveStock(toDto(portfolio.findStock(symbol)));
        flush(portfolio);
   
    }
       
}
 
 