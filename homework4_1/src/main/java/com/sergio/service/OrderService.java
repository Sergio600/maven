package com.sergio.service;

import com.sergio.domain.Order;
import com.sergio.domain.PriceList;
import com.sergio.domain.Product;
import com.sergio.exception.InvalidArgumentException;
import com.sergio.exception.OrderNotFoundException;
import com.sergio.repository.OrderRepository;
import com.sergio.repository.UserRepository;

import java.util.List;


/**
 * Order service class.
 */
public class OrderService {

    /**
     * Creates order and returns saved order.
     *
     * @param customer customer name.
     * @return created order.
     */
    public static Order createOrGetOrder(String customer) {
        if (customer == null) {
            throw new InvalidArgumentException("Name can't be null");
        }
        if(UserRepository.getByName(customer).isPresent()) {
            return (Order) UserRepository.getByName(customer).get();
        }
        else {
            return OrderRepository.save(new Order(customer));
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
