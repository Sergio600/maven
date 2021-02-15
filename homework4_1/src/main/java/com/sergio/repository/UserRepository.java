package com.sergio.repository;

import com.sergio.domain.Order;
import com.sergio.domain.User;
import com.sergio.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    /**+
     * returns order choosed by name of customer
     * @param customer
     * @return
     */
    public static Optional<Object> getByName(String customer) {
        List<Order> orders = OrderRepository.getOrders();
        for (Order order : orders) {
            if (order.getCustomer().equals(customer)) {
                return Optional.of(order);
            }
        }
        return Optional.empty();
    }

    /**
     * Adds user to
     * @param user
     * @return
     */
    public static User save (User user){
        Connection conn = SqlHelper.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO USER(ID, LOGIN) VALUES (?,?)");
            ps.setInt(1, user.getUserId());
            ps.setString(2, user.getName());
            

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return user;
    }
}
