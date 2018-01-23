package com.arki.filter;

import com.arki.laboratory.common.Logger;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Logger.info("EncodingFilter doFilter start...");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
        Logger.info("EncodingFilter doFilter finished!");
    }

    @Override
    public void destroy() {

    }
}
