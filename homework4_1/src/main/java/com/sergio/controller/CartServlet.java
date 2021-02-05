package com.sergio.controller;

import com.sergio.domain.Order;
import com.sergio.domain.Product;
import com.sergio.exception.OrderNotFoundException;
import com.sergio.repository.OrderRepository;
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
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String[] s = req.getParameterValues("goods");
        String id = req.getParameter("id");

        Order order = OrderService.addProducts(id, s);

        String name = order.getCustomer();
        List<Product> products = order.getProducts();

        double totalPrice = order.getTotalPrice();




        req.setAttribute("products", products);
        req.setAttribute("name", name);
        req.setAttribute("totalPrice", totalPrice);

        req.setAttribute("id", id);
        req.getRequestDispatcher("WEB-INF/jsp/cart.jsp").forward(req, resp);



    }
}
