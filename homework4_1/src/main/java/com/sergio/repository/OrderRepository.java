package com.sergio.repository;

import com.sergio.domain.Order;
import com.sergio.domain.User;
import com.sergio.service.UserService;
import com.sergio.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Repository class that emulate data base and has some repository methods.
 */
public class OrderRepository {


    /**
     * Saves order in "data base" (list of orders).
     *
     * @param order it's order to save.
     * @return saved order.
     */
    public static Order save(Order order) {
        Connection connection = SqlHelper.getConnection();

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO ORDER(USER_ID, TOTAL_PRICE) VALUES (?,?)");
            ps.setInt(1, order.getUser().getUserId());
            ps.setDouble(2, order.getTotalPrice());
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return order;
    }

    /**
     * Returns order by id if order is exist.
     *
     * @param user order id.
     * @return order by id if order is exist.
     */
    public static Order getByUser(User user) {
        Connection connection = SqlHelper.getConnection();
        Order order = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * from orders where user_id ='?'");
            ps.setInt(1, user.getUserId());
            ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                order.setId(rs.getInt(1));
                order.getUser().setUserId(rs.getInt(2));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

//    public static Optional<Object> getOrderByUserName(String customer) {
//        List<Order> orders = OrderRepository.getOrders();
//        for (Order order : orders) {
//            if (order.getUser().getName().equals(customer)) {
//                return Optional.of(order);
//            }
//        }
//        return Optional.empty();
//    }
}


