package com.sergio.repository;

import com.sergio.domain.Order;

import java.util.List;
import java.util.Optional;

public class UserRepository {

    public static Optional<Object> getByName(String customer) {

        List<Order> orders = OrderRepository.getOrders();

        for (Order order : orders) {
            if (order.getCustomer().equals(customer)) {
                return Optional.of(order);
            }
        }
        return Optional.empty();
    }
}
