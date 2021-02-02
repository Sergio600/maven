package com.sergio.controller;

import com.sergio.domain.Order;
import com.sergio.domain.PriceList;
import com.sergio.service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String name = req.getParameter("name");


        Order order = OrderService.createOrder(name);
        Map<String, Double> goods = PriceList.getPRODUCTS();

        String html = "";
        for (Map.Entry<String, Double> entry : goods.entrySet()) {
            html += String.format("<option value=\"%s\">%s %s$</option>",
                    entry.getKey(),
                    entry.getKey(),
                    entry.getValue());
        }

        req.setAttribute("name", name);
        req.setAttribute("html", html);

        req.getRequestDispatcher("jsp/products.jsp").forward(req, resp);


    }
}




//        Writer writer = resp.getWriter();
//        String htmlStr = "<!DOCTYPE html>\n" +
//                "<html lang=\"en\">\n" +
//                "\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\">\n" +
//                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
//                "    <title>Document</title>\n" +
//                "</head>\n" +
//                "\n" +
//                "<body>\n" +
//                "    <div>Welcome, " + name + "!!!</div>\n" +
//                "    <div>Make your order</div>\n" +
//                "    <div>\n" +
//                "        <form method=\"POST\" action=\"cart\">\n" +
//                "            <select name=\"goods\" size=\"1\">\n" +
//                html +
//                "            </select>\n" +
//                "            <input type=\"hidden\" name=\"id\" value=\"" + order.getId() + "\"></input>\n" +
//                "            </br>" +
//                "            <input type=\"submit\"></input>\n" +
//                "        </form>\n" +
//
//                "    </div>\n" +
//                "</body>\n" +
//                "\n" +
//                "</html>";
//
//        writer.write(htmlStr);
//
//        writer.close();