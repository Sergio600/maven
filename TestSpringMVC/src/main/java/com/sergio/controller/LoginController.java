package com.sergio.controller;

import com.sergio.domain.Product;
import com.sergio.service.OrderService;
import com.sergio.service.ProductService;
import com.sergio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.sql.SQLException;


@Controller
public class LoginController {
    private OrderService orderService;
    private UserService userService;
    private ProductService productService;

    @Autowired
    public LoginController(OrderService orderService, UserService userService, ProductService productService) {
        this.orderService = orderService;
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String goTOLoginPage(){
        return "welcome";
    }

    @RequestMapping("/login")
    public String helloPage(Model model, Principal principal) throws SQLException {
        if(principal!=null){
            System.out.println(principal.getName());
            model.addAttribute("products", productService.getAllProducts());
            model.addAttribute("order", orderService.createOrGetOrder(userService.createOrGetUser(principal.getName())));
            return "chooseproducts";
        } else {
            return "welcome";
        }
    }

    @PostMapping("/logout")
    public String logout(){
        return "redirect:/login?logout";
    }
}
