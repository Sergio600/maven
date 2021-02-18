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

    public static Order updateOrderTotalPrice(Order order){
        Connection connection = SqlHelper.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("UPDATE ORDERS SET TOTAL_PRICE = ? WHERE ID=?;");
            ps.setDouble(1, order.getTotalPrice());
            ps.setInt(2, order.getId());
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return order;
    }
}




