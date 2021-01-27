package com.sergio.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet(name="CartServlet", urlPatterns="/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        double sum=0;
        String[] s = req.getParameterValues("goods");

        for (String good:s) {
            double price = Double.parseDouble(good);
            sum+=price;
            System.out.println(good);
        }



        Writer writer = resp.getWriter();
        writer.write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Choosed goods</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div>Total price of your goods: "+sum+ "!</div>\n" +
                "</body>\n" +
                "\n" +
                "</html>");

    }
}
