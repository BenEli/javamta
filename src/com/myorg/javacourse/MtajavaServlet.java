package com.myorg.javacourse;

import java.io.IOException;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class MtajavaServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		resp.getWriter().println("Hello, ELI");
	
	}
}
