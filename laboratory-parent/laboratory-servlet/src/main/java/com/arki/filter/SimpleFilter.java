package com.arki.filter;

import com.arki.laboratory.common.Logger;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

public class SimpleFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String filterName = filterConfig.getFilterName();
        Logger.info("SimpleFilter init start... Filter name: [{}]", filterName);
        Enumeration<String> initParameterNames = filterConfig.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String element = initParameterNames.nextElement();
            Logger.info("SimpleFilter init parameter: [{}]=[{}]", element, filterConfig.getInitParameter(element));
        }
        Logger.info("SimpleFilter init finished!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Logger.info("SimpleFilter doFilter start...");
        chain.doFilter(request, response);
        Logger.info("SimpleFilter doFilter finished!");
    }

    @Override
    public void destroy() {
        Logger.info("SimpleFilter destroyed!");
    }
}
