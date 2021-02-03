package com.sergio.controller;

import com.sergio.domain.Order;
import com.sergio.domain.Product;
import com.sergio.service.OrderService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@WebServlet(name="CartServlet", urlPatterns="/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String[] s = req.getParameterValues("goods");
        String id = req.getParameter("id");

        Order order = OrderService.addProducts(id, s);
        String name = order.getCustomer();
        List<Product> products = order.getProducts();

        double totalPrice = order.getTotalPrice();

        String orderInfo="";
        int i=0;
        for (Product product: products) {
            i++;
            orderInfo += String.format("<p>%s) %s %s$</p>",
                    i,
                    product.getName(),
                    product.getPrice());
        }

        req.setAttribute("orderInfo", orderInfo);
        req.setAttribute("name", name);

        req.setAttribute("totalPrice", totalPrice);
        req.getRequestDispatcher("jsp/cart.jsp").forward(req, resp);



    }
}
//    Writer writer = resp.getWriter();
//
//    String cartStr = "<!DOCTYPE html>\n" +
//            "<html lang=\"en\">\n" +
//            "\n" +
//            "<head>\n" +
//            "    <meta charset=\"UTF-8\">\n" +
//            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
//            "    <title>Choosed goods</title>\n" +
//            "</head>\n" +
//            "\n" +
//            "<body>\n" +
//            "    <div>Dear, "+name+", your order is: </div>\n" +
//            orderInfo +
//            "    <div>Total price is: "+totalPrice+"$</div>\n" +
//            "</body>\n" +
//            "\n" +
//            "</html>";
//        writer.write(cartStr);
//                writer.close();