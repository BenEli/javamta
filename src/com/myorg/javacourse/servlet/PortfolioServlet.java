package com.myorg.javacourse.servlet;
import com.myorg.javacourse.service.*;
import com.myorg.javacourse.model.*;
import java.io.IOException;
import javax.servlet.http.*;
/**
 * servlet that create 2 portfolios change them and print them
 * @author ben and eli
 */
@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		
		PortfolioManager portfolioManager = new PortfolioManager();
		Portfolio portfolio1 = portfolioManager.getPortfolio();
		
		Portfolio portfolio2 = new Portfolio(portfolio1);
		
		portfolio2.setTitle("Portfolio #2");
	
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
		portfolio1.removeStock(0);
		
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
		portfolioManager.changeStockBid(portfolio2, portfolio2.getPortfolioSize() - 1, 55.55f);
		
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
	}
}