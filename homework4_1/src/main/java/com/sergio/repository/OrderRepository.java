package com.sergio.repository;

import com.sergio.domain.Order;
import com.sergio.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Repository class that emulate data base and has some repository methods.
 */
public class OrderRepository {

    private static List<Order> orders = Collections.synchronizedList(new ArrayList<>());

    private OrderRepository() {
    }

    public static List<Order> getOrders() {
        return orders;
    }

    /**
     * Saves order in "data base" (list of orders).
     *
     * @param order it's order to save.
     * @return saved order.
     */
    public static Order save(Order order) {

        orders.add(order);
        SqlHelper.addNewOrder(order);
        return order;
    }

    /**
     * Returns order by id if order is exist.
     *
     * @param id order id.
     * @return order by id if order is exist.
     */
    public static Optional<Order> getById(String id) {
        for (Order order : orders) {
            if (order.getId() == (Integer.parseInt(id))) {
                return Optional.of(order);
            }
        }
        return Optional.empty();
    }

    public static Optional<Object> getOrderByUserName(String customer) {
        List<Order> orders = OrderRepository.getOrders();
        for (Order order : orders) {
            if (order.getUser().getName().equals(customer)) {
                return Optional.of(order);
            }
        }
        return Optional.empty();
    }
}


