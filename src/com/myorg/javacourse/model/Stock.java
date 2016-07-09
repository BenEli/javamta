package com.myorg.javacourse.model;
import java.util.Date ;
import java.util.Calendar;
 
import org.algo.model.StockInterface;
 
/**
 * Represents a financial stock
 * include the next information about the stock:
 * stock symbol, stock ask, stock bid, date, recommendation about the stock, quantity of stock
 * @author ben and eli
 *
 */
public class Stock implements StockInterface {
        /**
         * group of potential orders about what to do with the stock
         */
        private ALGO_RECOMMENDATION recommendation;
        private String symbol ;
        private float  ask;
        private float bid;
        private Date data;
        private int stockQuantity;
       
        public enum ALGO_RECOMMENDATION{BUY, SELL, REMOVE, HOLD, NONE};
       
        public Stock(){
            data = new Date();/////////////////////////
            recommendation = ALGO_RECOMMENDATION.NONE;
            symbol = new String("");
        }
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
        public Stock(String symbol, float ask, float bid, Date date, int stockQuantity, ALGO_RECOMMENDATION recommendation){
            this.setSymbol(symbol);
            this.setAsk(ask);
            this.setBid(bid);
            this.setDate(date);
            this.setStockQuantity(stockQuantity);
            this.setRecommendation(recommendation);
        }
        
        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }
       
        public void setAsk(float ask) { // check if must have
            this.ask = ask;
        }
       
        public void setBid(float bid) { // check if must have
            this.bid = bid;
        }
       
        public void setDate(Date data) { // check if must have probably yes
            this.data = new Date(data.getTime());  
        }
       
        public void setStockQuantity(int stockQuantity){ // check if must have
            this.stockQuantity = stockQuantity;
        }
       
        public void setRecommendation(ALGO_RECOMMENDATION order){ // check if must have
            this.recommendation = order;
        }
       
        public String getSymbol() {
            return symbol;
        }
       
        public float getAsk() {
            return ask;
        }
       
        public float getBid() {
            return bid;
        }
       
        public Date getDate() { // check if ok
            return data;
        }
       
        public int getStockQuantity(){
            return stockQuantity;
        }
       
        public ALGO_RECOMMENDATION getRecommendation(){
            return this.recommendation;
        }
       
        /**
         * if possible update the stock quantity
         * @param quantity is the quantity of stocks to add or sub(if minus) to the stock quantity
         */
        public void updateQuantity(int quantity){
            if(stockQuantity + quantity >= 0)
                stockQuantity += quantity;
            else
                System.out.println("error cant update stock quantity");
        }
   
        /**
         * get part of the information about the stock
         * @return the next information about the stock as html string: symbol, ask, bid, date, quantity and total value
         */
        public String getHtmlDescription() {        
            String  stockDetails = ( "<b>stock symbol</b> :" +  getSymbol()  +" <b>ask</b> :"+getAsk()
                +" <b>Bid</b> :" + getBid() + " <b>date</b> :" + data.getDate() + "/" +data.getMonth()+ "/" +data.getYear())
                +" <b>Quantity</b> :" + getStockQuantity() + " <b> Total value</b> :" + getBid() * getStockQuantity();
            return stockDetails ;
        }      
}
