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
    @Autowired
    Connection connection;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

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

    @GetMapping("/products")
    public String showProductsPage(Model model) throws SQLException {
        List<Product> products = new ArrayList<>();

        PreparedStatement ps = connection.prepareStatement("Select * from good");
        ps.execute();
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            products.add(new Product(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }
        model.addAttribute("products", products);

        return "first/goodbye";
    }
}
