package com.sergio.service;

import com.sergio.domain.Order;
import com.sergio.domain.PriceList;
import com.sergio.domain.Product;
import com.sergio.domain.User;
import com.sergio.exception.InvalidArgumentException;
import com.sergio.exception.OrderNotFoundException;
import com.sergio.repository.OrderRepository;
import com.sergio.repository.UserRepository;
import com.sergio.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderService {

    /**
     * Creates order and returns saved order.
     *
     * @param user .
     * @return created order.
     */
    public static Order createOrGetOrder(User user) {
        if (user == null) {
            throw new InvalidArgumentException("Name can't be null");
        }

        if (UserRepository.getByName(user.getName()).isPresent()) {
            return (Order) OrderRepository.getOrderByUserName(user.getName()).get();

        } else {
            Order order = OrderRepository.save(new Order(user));
            return order;
        }
    }

    /**
     * Adds product to order.
     *
     * @param id               order id.
     * @param selectedProducts string array of product keys from product map.
     * @return order with saved order.
     */
    public static Order addProducts(String id, String[] selectedProducts) {
        if (id == null || selectedProducts == null) {
            throw new InvalidArgumentException("Arguments cant be null");
        }
        Order order;
        if (OrderRepository.getById(id).isPresent()) {
            order = OrderRepository.getById(id).get();
        } else {
            throw new OrderNotFoundException("Order not found");
        }

        List<Product> products = order.getProducts();
        for (String productName : selectedProducts) {

            Product product = new Product();
            product.setName(productName);
            product.setPrice(PriceList.getPRODUCTS().get(productName));
            products.add(product);
            order.setTotalPrice(calcTotalPrice(order));
        }

        order.setProducts(products);
        SqlHelper.updateOrderTotalPrice(order);

        return order;
    }

    /**
     * Removes product from order
     *
     * @param id
     * @param index
     * @return order with removed product
     */
    public static Order removeProduct(String id, int index) {
        Order order;
        if (OrderRepository.getById(id).isPresent()) {
            order = OrderRepository.getById(id).get();
        } else {
            throw new OrderNotFoundException("Order not found");
        }

        List<Product> products = order.getProducts();
        products.remove(index);
        order.setProducts(products);

        SqlHelper.updateOrderTotalPrice(order);

        return order;
    }

    /**
     * Calculates total price of order.
     *
     * @param order order for calculation.
     * @return total price.
     */
    private static double calcTotalPrice(Order order) {
        double totalPrice = 0.0;
        for (Product product : order.getProducts()) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
}
