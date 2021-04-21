package com.sergio.controller;

import com.sergio.service.OrderService;
import com.sergio.service.ProductService;
import com.sergio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;


@Controller
@RequestMapping("/")
public class WelcomeController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @RequestMapping
    protected String doWelcome(Principal principal, ModelMap model) {

        return "welcome";


    }
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

}
