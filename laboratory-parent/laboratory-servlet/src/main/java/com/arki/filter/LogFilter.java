package com.arki.filter;

import com.arki.laboratory.common.ArrayUtil;
import com.arki.laboratory.common.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

public class LogFilter  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Logger.info("LogFilter doFilter start...");
        if(request instanceof HttpServletRequest){
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            StringBuffer requestURL = httpRequest.getRequestURL();
            Logger.info("Request URL: [{}]", requestURL);
        }
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            Logger.info("Request attribute: [{}]=[{}]", attributeName, request.getAttribute(attributeName));
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            Logger.info("Request parameter: [{}]={}", entry.getKey(), ArrayUtil.transferArrayToString(entry.getValue()));
        }
        chain.doFilter(request, response);
        int bufferSize = response.getBufferSize();
        Logger.info("Response buffer used: [{}]", bufferSize);
        Logger.info("LogFilter doFilter finished");
    }

    @Override
    public void destroy() {

    }
}
