package com.sergio.controller;

import com.sergio.repository.ProductRepository;
import com.sergio.service.OrderService;
import com.sergio.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "WelcomeServlet", urlPatterns = "/")
public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("customer") != null) {
            String customer = session.getAttribute("customer").toString();
            req.setAttribute("products", ProductRepository.getAllProducts());

            req.setAttribute("order", OrderService.createOrGetOrder(UserService.createOrGetUser(customer)));
            req.getRequestDispatcher("WEB-INF/jsp/chooseproducts.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("WEB-INF/jsp/welcome.jsp").forward(req, resp);
        }
    }
}

