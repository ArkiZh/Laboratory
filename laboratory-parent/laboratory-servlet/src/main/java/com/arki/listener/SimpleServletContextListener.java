package com.arki.listener;

import com.arki.laboratory.common.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Enumeration;

public class SimpleServletContextListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent event) {
        Logger.info("Servlet context initialization start...");
        ServletContext servletContext = event.getServletContext();

        String contextPath = servletContext.getContextPath();
        Logger.info("ContextPath: [{}]", contextPath);

        Enumeration<String> initParameterNames = servletContext.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String s = initParameterNames.nextElement();
            String initParameter = servletContext.getInitParameter(s);
            Logger.info("initParameter: [{}]=[{}]", s, initParameter);
        }

        Logger.info("Servlet context initialization finished!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        Logger.info("Servlet context destruction start...");
        Logger.info("Servlet context destruction finished!");
    }
}
