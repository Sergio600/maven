package com.sergio.controller;

import com.sergio.domain.Order;
import com.sergio.domain.Product;
import com.sergio.domain.User;
import com.sergio.repository.OrderRepository;
import com.sergio.service.OrderService;
import com.sergio.service.ProductService;
import com.sergio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String makeOrder(ModelMap model, HttpServletRequest req, HttpSession session) {

        String customer;

        if (session.getAttribute("customer") == null && req.getParameter("customer") != null) {
            session.setAttribute("customer", req.getParameter("customer"));
            customer = req.getParameter("customer");
        } else {
            customer = session.getAttribute("customer").toString();
        }
        User user = userService.createOrGetUser(customer);
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
                order = orderService.addProducts(order, req.getParameter("selected"));
            }
        }

        order.setProducts(orderRepository.getProductsByOrder(order));

        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("selectedProducts", order.getProducts());
        model.addAttribute("order", user.getOrder());
        model.addAttribute("user", user);
        return "jsp/chooseproducts";

    }
}
