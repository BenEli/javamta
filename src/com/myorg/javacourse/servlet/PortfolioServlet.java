package com.myorg.javacourse.servlet;
import com.myorg.javacourse.service.*;
import com.myorg.javacourse.model.*;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		
		PortfolioManager portfolioManager = new PortfolioManager();
		Portfolio portfolio = portfolioManager.getPortfolio();
	
		
		resp.getWriter().println(portfolio.getHtmlString());
	}
}
