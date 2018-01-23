package com.arki.servlet;

import com.arki.laboratory.common.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PrefixMatchServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger.info("PrefixMatchServlet hit!");
        //super.service(req, resp);
        ServletContext servletContext = req.getServletContext();
        PrintWriter writer = resp.getWriter();
        writer.write("PrefixMatchServlet hit!");
        writer.flush();
        writer.close();
    }
}
