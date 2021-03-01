package com.sergio.controller;

import com.sergio.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class FirstController {
    @Autowired
    Connection connection;


    @GetMapping("/")
    public String helloPage(Model model) throws SQLException {
        List<Product> products = new ArrayList<>();

        PreparedStatement ps = connection.prepareStatement("Select * from good");
        ps.execute();
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            products.add(new Product(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }
        model.addAttribute("products", products);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage(){
        return "first/goodbye";
    }
}
