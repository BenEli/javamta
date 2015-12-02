package com.myorg.javacourse.service;

import java.util.Date;
import com.myorg.javacourse.*;
import com.myorg.javacourse.model.Portfolio;


public class PortfolioManager {

	Date date = new Date();
	
	public Portfolio getPortfolio(){
		Portfolio newPortfolio = new Portfolio("Ben and Eli portfolio");
		date.setDate(15);
		date.setMonth(11);
		date.setYear(2014);
		
		newPortfolio.addStock(new Stock("PIH", 13.1f, 12.4f, this.date));
		newPortfolio.addStock(new Stock("AAL", 5.78f, 5.5f, this.date));
		newPortfolio.addStock(new Stock("CAAS", 32.2f, 31.5f, this.date));
		return newPortfolio;
	}
}
