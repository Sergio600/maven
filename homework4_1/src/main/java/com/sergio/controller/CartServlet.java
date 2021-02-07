package com.sergio.controller;

import com.sergio.domain.Order;
import com.sergio.service.OrderService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="CartServlet", urlPatterns="/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = OrderService.createOrGetOrder(req.getParameter("customer"));
        req.setAttribute("order", order);
        req.getRequestDispatcher("WEB-INF/jsp/cart.jsp").forward(req, resp);
    }
}
