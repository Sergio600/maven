package com.sergio.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet (name="WelcomeServlet", urlPatterns="/")
public class WelcomeServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        Writer writer = resp.getWriter();
        writer.write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Online Shop</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div>Welcome to online Shop</div>\n" +
                "    <form method=\"POST\">\n" +
                "        <p><input type=\"text\" placeholder=\"Enter your name\"></p>\n" +
                "        <p><input type=\"submit\" value=\"Enter\"></p>\n" +
                "    </form>\n" +
                "</body>\n" +
                "\n" +
                "</html>");
    }
}
