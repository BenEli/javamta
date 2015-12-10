package com.myorg.javacourse.model;
import java.util.Date ;
import java.util.Calendar;

/**
 * Represents a financial stock
 * include the next information about the stock:
 * stock symbol, stock ask, stock bid, date, recommendation about the stock, quantity of stock
 * @author ben and eli
 *
 */
public class Stock {
		/**
		 * group of potential orders about what to do with the stock 
		 */
		enum Orders{BUY, SELL, REMOVE, HOLD};
		
		private String symbol ;
		private float  ask;
		private float bid;
		private Date data;
		/**
		 * recommendation about what to do with the stock: 0 for buy, 1 for sell, 2 for remove, 3 for hold
		 */
		private int recommendation;
		private int stockQuantity;
		/**
		 * copy c'tor
		 * @param newStock this is the stock the copy c'tor is copying
		 */
		public Stock(Stock newStock){
			this(newStock.symbol, newStock.ask, newStock.bid, newStock.data, newStock.stockQuantity, newStock.recommendation);
		}
		/**
		 * c'tor 
		 */
		public Stock(String symbol, float ask, float bid, Date date, int stockQuantity, int recommendation){
			this.setSymbol(symbol);
			this.setAsk(ask);
			this.setBid(bid);
			this.setData(date);
			this.setStockQuantity(stockQuantity);
			this.setRecommendation(recommendation);
		}
	
		public String getSymbol() {
			return symbol;
		}
		
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}
		
		public float getAsk() {
			return ask;
		}
		
		public void setAsk(float ask) {
			this.ask = ask;
		}
		
		public float getBid() {
			return bid;
		}
		
		public void setBid(float bid) {
			this.bid = bid;
		}
		
		public Date getData() {
			return data;
		}
		
		public void setData(Date data) {
			this.data = new Date(data.getTime());	
		}
		
		public void setStockQuantity(int stockQuantity){
			this.stockQuantity = stockQuantity;
		}
		
		public void setRecommendation(String order){
			switch (order){
			case "BUY":
			case "buy":
				this.recommendation = Orders.BUY.ordinal();
				break;
			case "SELL":
			case "sell":
				this.recommendation = Orders.SELL.ordinal();
				break;
			case "REMOVE":
			case "remove":
				this.recommendation = Orders.REMOVE.ordinal();
				break;
			case "HOLD":
			case "hold":
				this.recommendation = Orders.HOLD.ordinal();
				break;
			default:
				this.recommendation = -1;
			}
		}
		
		public void setRecommendation(int order){
			this.recommendation = order;
		}
		
		public int getRecommendation(){
			return this.recommendation; 
		}
		
		public int getrecommendation(){
			return recommendation;
		}
		
		public int getStockQuantity(){
			return stockQuantity;
		}
		/**
		 * get part of the information about the stock
		 * @return the next information about the stock as html string: symbol, ask, bid, date 
		 */
		public String getHtmlDescription() {         
			String  stockDetails = ( "<b>stock symbol</b> :" +  getSymbol()  +"   <b>ask</b> :"+getAsk()
				+"   <b>Bid</b> :" + getBid() + "   <b>date</b> :" + data.getDate() + "/" +data.getMonth()+ "/" +data.getYear());
			return stockDetails ;
		}
}
	
