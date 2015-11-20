package com.myorg.javacourse;

import java.util.Date;


public class Stock {
	private float ask;
	private float bid;
	private Date date;
	private String symbol;
		
	public Stock(){
		this(0, 0, null ,"unKnown");
	}
	
	public Stock(float ask, float bid, Date date, String symbol){
		setAsk(ask);
		setBid(bid);
		setDate(date);
		setSymbol(symbol);
	}
	
	public void setAsk(float ask){
		this.ask = ask;
	}

	public void setBid(float bid){
		this.bid = bid;
	}
	
	public void setSymbol(String symbol){
		this.symbol = symbol;
	}
	
	public void setDate(Date date){
		setDate(date.getDate(), date.getHours(), date.getMinutes(), date.getMonth(), date.getSeconds(), date.getTime(), date.getYear());
	}
	
 	public void setDate(int date, int hours, int minutes, int month, int seconds, long time, int year){
		this.date = new Date();
		
		this.date.setDate(date);
		this.date.setHours(hours);
		this.date.setMinutes(minutes);
		this.date.setMonth(month);
		this.date.setSeconds(seconds);
		this.date.setTime(time);
		this.date.setYear(year);
	}
	
	public float getAsk(){
		return this.ask;
	}
	
	public float getBid(){
		return this.bid;
	}
	
	public String getSymbol(){
		return this.symbol;
	}
	
	public Date getDate(){
		return this.date;
	}
	
	public String getHtmlDescription(){
		String dateAsString = this.date.getDate()+ "/" +this.date.getMonth()+ "/" + this.date.getYear();
		return "<b>Stock symbol:</b> " +this.symbol+ ", <b>ask: </b> " +this.ask+ ", <b>bid: </b>" +this.bid+ ",<b>Date: </b> " +dateAsString;       
	}
		
}
