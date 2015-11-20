package com.myorg.javacourse;

import java.io.IOException;
import java.util.Date;
import javax.servlet.http.*;
import com.myorg.javacourse.Stock;


@SuppressWarnings("serial")
public class StockDetailsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		
		Date d = new Date();
		d.setDate(11);
		d.setMonth(15);
		d.setYear(2014);
		
		
		Stock[] stocks = new Stock[3];
		
			stocks[0] = new Stock(13.1f, 12.4f, d , "PIH");
			stocks[1] = new Stock(5.78f, 5.5f, d, "AAL");
			stocks[2] = new Stock(32.2f, 31.5f, d, "CAAS");
		
			for(int i = 0; i < stocks.length; i++)
			{
				resp.getWriter().println(stocks[i].getHtmlDescription() + "<br>");
			}
	}
}
