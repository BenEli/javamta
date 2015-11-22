package com.myorg.javacourse;
import java.util.Date ;
import java.util.Calendar;
public class Stock {
	
		private String symbol ;
		private float  ask;
		private float bid;
		private java.util.Date data;
		private int day;
		private int month;
		private int year ;
	
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
		public java.util.Date getData() {
			return data;
		}
		public void setData(java.util.Date data) {
			this.data= data;	
		} 

		public String getHtmlDescription() {         
		String  stockDetails = ( "<b>stock symbol</b> :" +  getSymbol()  +"   <b>ask</b> :"+getAsk()+"   <b>Bid</b> :" + getBid() + "   <b>date</b> :" + data.getDate() + "/" +data.getMonth()+ "/" +data.getYear() );
		return stockDetails ;
		}
}
	

	

