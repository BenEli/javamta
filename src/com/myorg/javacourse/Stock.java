package com.myorg.javacourse;
import java.util.Date ;
import java.util.Calendar;
public class Stock {
	
		enum Orders{BUY, SELL, REMOVE, HOLD};
		private String symbol ;
		private float  ask;
		private float bid;
		private Date data;
		private int recommendation;
		private int stockQuantity;
		
		public Stock(){
			this("", 0f, 0f, null);
		}
		public Stock(String symbol, float ask, float bid, Date date){
			this.setSymbol(symbol);
			this.setAsk(ask);
			this.setBid(bid);
			this.setData(date);
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
			this.data= data;	
		}
		public int getrecommendation(){
			return recommendation;
		}
		public int getStockQuantity(){
			return stockQuantity;
		}

		public String getHtmlDescription() {         
		String  stockDetails = ( "<b>stock symbol</b> :" +  getSymbol()  +"   <b>ask</b> :"+getAsk()+"   <b>Bid</b> :" + getBid() + "   <b>date</b> :" + data.getDate() + "/" +data.getMonth()+ "/" +data.getYear() );
		return stockDetails ;
		}
}
	

	

