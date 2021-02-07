package com.sergio.controller;

import com.sergio.domain.Order;
import com.sergio.domain.PriceList;
import com.sergio.service.OrderService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "ProductServlet", urlPatterns = "/chooseProducts")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String customer = req.getParameter("customer");

        if(session.getAttribute("customer") ==null && customer!=null) {
            session.setAttribute("customer", customer);
        }

        Order order = OrderService.createOrGetOrder(customer);
        System.out.println(order.getProducts());

        if (req.getParameterValues("selected")!=null){
            OrderService.addProducts(order.getCustomer(), req.getParameterValues("selected"));
        }

        req.setAttribute("products", PriceList.getPRODUCTS());
        req.setAttribute("order", OrderService.createOrGetOrder(customer));
        req.getRequestDispatcher("WEB-INF/jsp/products.jsp").forward(req, resp);


    }
}