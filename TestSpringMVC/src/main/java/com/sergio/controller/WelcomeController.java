package com.sergio.controller;

import com.sergio.domain.Product;
import com.sergio.service.OrderService;
import com.sergio.service.ProductService;
import com.sergio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class WelcomeController {
    private OrderService orderService;
    private UserService userService;
    private ProductService productService;

    @Autowired
    public WelcomeController(OrderService orderService, UserService userService, ProductService productService) {
        this.orderService = orderService;
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String helloPage(Model model, HttpSession session) throws SQLException {
        if(session.getAttribute("customer")!=null){
            String customer = session.getAttribute("customer").toString();
            model.addAttribute("products", productService.getAllProducts());
            model.addAttribute("order", orderService.createOrGetOrder(userService.createOrGetUser(customer)));
            return "jsp/chooseproducts";
        } else {
            return "jsp/welcome";
        }
    }
}
