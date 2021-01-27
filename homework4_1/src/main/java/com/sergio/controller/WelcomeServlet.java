package com.sergio.controller;

import com.sergio.domain.Product;
import com.sergio.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

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
                "        <p><input name =\"name\" type=\"text\" placeholder=\"Enter your name\"></p>\n" +
                "        <p><input type=\"submit\" value=\"Enter\"></p>\n" +
                "    </form>\n" +
                "</body>\n" +
                "\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");


        String name = req.getParameter("name");

        OrderService orderService = new OrderService();
        orderService.setCustomerName(name);

        String html ="";

        List<Product> products = new ArrayList<>();
        products.add(new Product("Mobile phone", 10.0));
        products.add(new Product("Book", 5.0));
        products.add(new Product("Pen", 1.0));

        for (Product product: products) {
            html+=String.format("<option value=\"%s\">%s %s$</option>",
                    product.getPrice(),
                    product.getName(),
                    product.getPrice()) ;
        }

        Writer writer = resp.getWriter();
        writer.write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div>Welcome, "+ name + "!!!</div>\n" +
                "    <div>Make your order</div>\n" +
                "    <div>\n" +
                "        <form method=\"POST\" action=\"/homework4_1/cart\">\n" +
                "            <select name=\"goods\" multiple>\n size=\"1\">"  +
                                html+
                "            </select>\n" +
                "            </br>"+
                "            <input type=\"submit\"></input>\n" +
                "        </form>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>");
    }
}
