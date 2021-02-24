package com.sergio.controller;

import com.sergio.SpringContext;
import com.sergio.domain.Order;
import com.sergio.domain.User;
import com.sergio.repository.OrderRepository;
import com.sergio.repository.ProductRepository;
import com.sergio.service.OrderService;
import com.sergio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="CartServlet", urlPatterns="/cart")
public class CartServlet extends HttpServlet {

    private OrderService orderService;
    private UserService userService;
    private OrderRepository orderRepository;

    @Override
    public void init() {
        AnnotationConfigApplicationContext context = SpringContext.getApplicationContext();
        this.orderService = (OrderService) context.getBean(OrderService.class);
        this.userService = (UserService) context.getBean(UserService.class);
        this.orderRepository = (OrderRepository) context.getBean(OrderRepository.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String customer;


        if(req.getParameter("exit")!=null){
            session.invalidate();
            req.getRequestDispatcher("WEB-INF/jsp/welcome.jsp").forward(req, resp);
            return;
        }
        customer = req.getParameter("customer");

        User user = userService.createOrGetUser(customer);
        Order order = orderService.createOrGetOrder(user);
        order.setProducts(orderRepository.getProductsByOrder(order));

        req.setAttribute("order", order);
        req.getRequestDispatcher("WEB-INF/jsp/cart.jsp").forward(req, resp);
    }
}
