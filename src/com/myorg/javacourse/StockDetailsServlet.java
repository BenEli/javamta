package com.myorg.javacourse;
import com.myorg.javacourse.Stock;
import java.io.IOException;
import javax.servlet.http.*;
import java.util.Date ;


@SuppressWarnings("serial")
public class StockDetailsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		
		Stock[] stocks = {new Stock() ,new Stock() ,new Stock()};
		java.util.Date date = new java.util.Date();
		
		String symbol[]= {" PIH " ," AAL " , " CAAS " } ;
		float ask[]={13.1f,5.78f,32.2f};
		float bid[]={12.4f,5.5f,31.5f};
		date.setDate(15);
		date.setMonth(11);
		date.setYear(2014);
		
			for(int i = 0 ; i < stocks.length  ; i++){
				stocks[i].setSymbol(symbol[i]);
				stocks[i].setAsk(ask[i]);
				stocks[i].setBid(bid[i]);
				stocks[i].setData(date);
			}
		
			resp.getWriter().println(" <b>Stocks details are :</b> " + "<br>" );
			
			for(int i = 0 ; i < stocks.length  ; i++){
				resp.getWriter().println( stocks[i].getHtmlDescription()  + "<br>");
			}
	}
}

		
		
	
