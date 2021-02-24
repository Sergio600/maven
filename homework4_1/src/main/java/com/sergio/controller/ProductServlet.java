package com.sergio.controller;

import com.sergio.SpringContext;
import com.sergio.domain.Order;
import com.sergio.domain.User;
import com.sergio.repository.OrderRepository;
import com.sergio.service.OrderService;
import com.sergio.service.ProductService;
import com.sergio.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "ProductServlet", urlPatterns = "/chooseproducts")
public class ProductServlet extends HttpServlet {

    private OrderService orderService;
    private UserService userService;
    private ProductService productService;
    private OrderRepository orderRepository;

    @Override
    public void init() {
        AnnotationConfigApplicationContext context = SpringContext.getApplicationContext();
        this.orderService = (OrderService) context.getBean(OrderService.class);
        this.userService = (UserService) context.getBean(UserService.class);
        this.productService=(ProductService) context.getBean(ProductService.class);
        this.orderRepository = (OrderRepository) context.getBean(OrderRepository.class);
    }

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

        User user = userService.createOrGetUser((customer));
        Order order = orderService.createOrGetOrder(user);

        /**
         * Check request parameter remove
         * if its != null than call method removeProduct to remove by index of product
         */
        if (req.getParameter("remove") != null) {
            int idToRemoveProduct = Integer.parseInt(req.getParameter("remove"));
            order = orderService.removeProduct(order, idToRemoveProduct);
        } else {
            if (req.getParameterValues("selected") != null) {
                order = orderService.addProducts(order, req.getParameterValues("selected"));
            }
        }

        order.setProducts(orderRepository.getProductsByOrder(order));

        req.setAttribute("products", productService.getAllProducts());
        req.setAttribute("order", order);
        req.getRequestDispatcher("WEB-INF/jsp/chooseproducts.jsp").forward(req, resp);
    }
}