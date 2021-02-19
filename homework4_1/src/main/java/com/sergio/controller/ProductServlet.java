package com.sergio.controller;

import com.sergio.domain.Order;
import com.sergio.domain.User;
import com.sergio.repository.ProductRepository;
import com.sergio.service.OrderService;
import com.sergio.service.UserService;
import com.sergio.sql.SqlHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "ProductServlet", urlPatterns = "/chooseproducts")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String customer;

        if (session.getAttribute("customer") == null && req.getParameter("customer") != null) {
            session.setAttribute("customer", req.getParameter("customer"));
            customer = req.getParameter("customer");
        } else {
            customer = session.getAttribute("customer").toString();
        }

        User user = UserService.createOrGetUser((customer));
        Order order = OrderService.createOrGetOrder(user);



        /**
         * Check request parameter remove
         * if its != null than call method removeProduct to remove by index of product
         */
        if (req.getParameter("remove") != null) {
            int idToRemoveProduct = Integer.parseInt(req.getParameter("remove"));
            order = OrderService.removeProduct(order, idToRemoveProduct);
        } else {
            if (req.getParameterValues("selected") != null) {
                order = OrderService.addProducts(order, req.getParameterValues("selected"));

            }
        }

        SqlHelper.showUsers();
        SqlHelper.showOrders();
        SqlHelper.showOrdersGood();

        req.setAttribute("products", ProductRepository.getAllProducts());
        req.setAttribute("order", order);
        req.getRequestDispatcher("WEB-INF/jsp/chooseproducts.jsp").forward(req, resp);
    }
}