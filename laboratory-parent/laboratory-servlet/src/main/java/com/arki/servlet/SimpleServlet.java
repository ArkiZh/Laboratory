package com.arki.servlet;

import com.arki.laboratory.common.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleServlet extends HttpServlet {


    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        Logger.info("Service method start...");
        ServletContext servletContext = req.getServletContext();
        Logger.info("Context path: [{}]", servletContext.getContextPath());
        super.service(req, res);
        Logger.info("Service method finished...");
    }
}
