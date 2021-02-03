package com.sergio.controller;

import com.sergio.domain.PriceList;
import com.sergio.service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.Writer;

@WebServlet(name = "WelcomeServlet", urlPatterns = "/")
public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        if (req.getSession().getAttribute("customer")!=null){
//            req.setAttribute("products", PriceList.getPRODUCTS());
            req.setAttribute("order", OrderService.createOrder((String) req.getSession().getAttribute("customer")));
            req.getRequestDispatcher("WEB-INF/jsp/products.jsp").forward(req, resp);

        } else {

            req.getRequestDispatcher("WEB-INF/jsp/welcome.jsp").forward(req, resp);
        }



    }
}



//        Writer writer = resp.getWriter();
//
//        String str = "<!DOCTYPE html>\n" +
//                "<html lang=\"en\">\n" +
//                "\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\">\n" +
//                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
//                "    <title>Online Shop</title>\n" +
//                "</head>\n" +
//                "\n" +
//                "<body>\n" +
//                "    <div>Welcome to online Shop</div>\n" +
//                "    <form method=\"POST\" action=\"products\">\n" +
//                "        <p><input name =\"name\" type=\"text\" placeholder=\"Enter your name\"></p>\n" +
//                "            <input type=\"checkbox\" name=\"checkbox\" value=\"checkbox\"> I agree with terms os service <Br>\n" +
//                "        <p><input type=\"submit\" value=\"Enter\"></p>\n" +
//                "    </form>\n" +
//                "</body>\n" +
//                "\n" +
//                "</html>";
//        System.out.println(str);
//        writer.write(str);
//        writer.close();

