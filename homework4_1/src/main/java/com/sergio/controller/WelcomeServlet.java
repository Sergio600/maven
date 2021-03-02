//package com.sergio.controller;
//
//import com.sergio.SpringContext;
//import com.sergio.service.OrderService;
//import com.sergio.service.ProductService;
//import com.sergio.service.UserService;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//import java.io.IOException;
//
//@WebServlet(name = "WelcomeServlet", urlPatterns = "/")
//public class WelcomeServlet extends HttpServlet {
//
//    private OrderService orderService;
//    private UserService userService;
//    private ProductService productService;
//
//    @Override
//    public void init() {
//        AnnotationConfigApplicationContext context = SpringContext.getApplicationContext();
//        this.orderService = (OrderService) context.getBean(OrderService.class);
//        this.userService = (UserService) context.getBean(UserService.class);
//        this.productService=(ProductService) context.getBean(ProductService.class);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//
//        if (session.getAttribute("customer") != null) {
//            String customer = session.getAttribute("customer").toString();
//            req.setAttribute("products", productService.getAllProducts());
//
//            req.setAttribute("order", orderService.createOrGetOrder(userService.createOrGetUser(customer)));
//            req.getRequestDispatcher("WEB-INF/jsp/chooseproducts.jsp").forward(req, resp);
//        } else {
//            req.getRequestDispatcher("WEB-INF/jsp/welcome.jsp").forward(req, resp);
//        }
//    }
//}
//
