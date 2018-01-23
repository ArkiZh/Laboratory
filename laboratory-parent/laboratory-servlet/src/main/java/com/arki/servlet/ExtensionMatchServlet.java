package com.arki.servlet;

import com.arki.laboratory.common.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ExtensionMatchServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger.info("ExtensionMatchServlet hit!");
        PrintWriter writer = resp.getWriter();
        writer.write("ExtensionMatchServlet hit!");
        writer.close();
    }
}
