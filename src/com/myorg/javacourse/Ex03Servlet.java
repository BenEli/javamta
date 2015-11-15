package com.myorg.javacourse;
import com.myorg.javacourse.MathManager;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Ex03Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		
		double radius = 50 , areaOfACircle ;
		double angleB = 30 , hypotenus  =50 , opposite;
		double base = 20 , exp =13 ,power ;
		
		MathManager.setRadius(radius);
		MathManager.setTriangle(angleB ,hypotenus);
		MathManager.setBaseNExp(base , exp );
				
		MathManager mathManager = new MathManager();
		String resultStr = mathManager.getResults();
		resp.getWriter().println(" The results are : " + resultStr  + "  ");
	}
}
