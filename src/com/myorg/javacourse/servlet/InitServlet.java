package com.myorg.javacourse.servlet;
import com.myorg.javacourse.service.*;
import com.myorg.javacourse.model.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.algo.service.ServiceManager;
/**
 * servlet that create  portfolio change and print 
 * @author ben and eli
 */
@SuppressWarnings("serial")
public class InitServlet extends javax.servlet.http.HttpServlet {
   
    public void init() throws ServletException {
        PortfolioManager pm = new PortfolioManager();
        ServiceManager.setPortfolioManager(pm);
        super.init();
    }
}