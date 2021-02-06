package com.sergio.controller;

import com.sergio.domain.Order;
import com.sergio.domain.PriceList;
import com.sergio.domain.Product;
import com.sergio.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String customer = req.getParameter("customer");

        session.setAttribute("customer", customer);




//        if (req.getParameterValues("goods")!=null){
//
//            Order order = OrderService.getOrder(customer);
//            OrderService.addProducts(order.getCustomer(), req.getParameterValues("goods"));
//        }



        Map<String, Double> products = PriceList.getPRODUCTS();

        req.setAttribute("products", products);

        req.getRequestDispatcher("WEB-INF/jsp/products.jsp").forward(req, resp);


    }
}