package com.sergio.controller;

import com.sergio.domain.Order;
import com.sergio.domain.User;
import com.sergio.repository.OrderRepository;
import com.sergio.service.OrderService;
import com.sergio.service.ProductService;
import com.sergio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/chooseproducts")
public class ProductController {
    private OrderService orderService;
    private UserService userService;
    private ProductService productService;
    private OrderRepository orderRepository;

    @Autowired
    public ProductController(OrderService orderService, UserService userService, ProductService productService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.userService = userService;
        this.productService = productService;
        this.orderRepository = orderRepository;
    }

    @RequestMapping
    public String makeOrder(ModelMap model, Principal principal, @RequestParam(value = "selected") String[] selected) {
        User user = userService.createOrGetUser(principal.getName());

        if (user != null) {
            orderService.addProducts(user.getOrder(), selected);
        }


        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("order", user.getOrder());
        return "chooseproducts";

    }
}
