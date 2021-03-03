package com.sergio.controller;

import com.sergio.domain.Order;
import com.sergio.domain.User;
import com.sergio.repository.OrderRepository;
import com.sergio.service.OrderService;
import com.sergio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {
    private OrderService orderService;
    private UserService userService;
    private OrderRepository orderRepository;

    @Autowired
    public CartController(OrderService orderService, UserService userService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.userService = userService;
        this.orderRepository = orderRepository;
    }

    @RequestMapping
    public String showCart(Model model, HttpSession session, HttpServletRequest req) {

        String customer;

        if (req.getParameter("exit") != null) {
            session.invalidate();
            return "jsp/welcome";
        }

        customer = req.getParameter("customer");

        User user = userService.createOrGetUser(customer);
        Order order = orderService.createOrGetOrder(user);
        order.setProducts(orderRepository.getProductsByOrder(order));

        model.addAttribute("user", user);
        model.addAttribute("order", user.getOrder());
        model.addAttribute("selectedProducts", order.getProducts());
        model.addAttribute("totalPrice", order.getTotalPrice());
        return "jsp/cart";
    }
}



