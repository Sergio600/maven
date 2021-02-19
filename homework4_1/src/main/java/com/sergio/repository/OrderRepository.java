package com.sergio.repository;

import com.sergio.domain.Order;
import com.sergio.domain.Product;
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
            ps = connection.prepareStatement("INSERT INTO ORDERS(USER_ID) VALUES (?)");
            ps.setInt(1, order.getUser().getUserId());
            ps.execute();

            ps = connection.prepareStatement("SELECT * from ORDERS where USER_ID=?;");
            ps.setInt(1, order.getUser().getUserId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                order.setId(rs.getInt("ID"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return order;
    }

    public static Order updateOrderTotalPrice(Order order){

        List<Product> products = order.getProducts();
        double totalPrice=0;
        for (Product product: products) {
            totalPrice+=product.getPrice();
        }

        Connection connection = SqlHelper.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("UPDATE ORDERS SET TOTAL_PRICE = ? WHERE ID=?;");
            ps.setDouble(1, totalPrice);
            ps.setInt(2, order.getId());
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return order;
    }
}




