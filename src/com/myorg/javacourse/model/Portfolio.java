package com.myorg.javacourse.model;

import com.myorg.javacourse.*;

public class Portfolio {
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	private Stock[] stocks;
	private short portfolioSize;
	
	public Portfolio(){
		this("");
	}
	
	public Portfolio(String title){
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.setTitle(title);
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void addStock(Stock newStock){
		this.stocks[portfolioSize++] = newStock;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public Stock[] getStocks(){
		return this.stocks;
	}
	
	public String getHtmlString(){
		String tempString = "<h1>" +this.title+ "</h1>";
		for(short i = 0; i < this.portfolioSize; i++)
			tempString = tempString.concat("<p>" +this.stocks[i].getHtmlDescription()+ "</p>");
		return tempString; 
	}
}
