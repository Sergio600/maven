package com.sergio.controller;

import com.sergio.domain.Order;
import com.sergio.domain.PriceList;
import com.sergio.service.OrderService;
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

        if(session.getAttribute("customer") == null && req.getParameter("customer")!=null) {
            session.setAttribute("customer", req.getParameter("customer"));
            customer = req.getParameter("customer");
            System.out.println("Имя пользователя из 1 пункта" + customer);
        } else {
            customer = session.getAttribute("customer").toString();
            System.out.println("Имя пользователя из 2 пункта" + customer);

            String [] str = req.getParameterValues("selected");
            System.out.println(str[0]);

        }

        Order order = OrderService.createOrGetOrder(customer);

        System.out.println("из текста"+order.getCustomer());

        if (req.getParameterValues("selected")!=null){
            OrderService.addProducts(order.getId()+"", req.getParameterValues("selected"));
        }

        req.setAttribute("products", PriceList.getPRODUCTS());
        req.setAttribute("order", OrderService.createOrGetOrder(customer));
        req.getRequestDispatcher("WEB-INF/jsp/chooseproducts.jsp").forward(req, resp);


    }
}