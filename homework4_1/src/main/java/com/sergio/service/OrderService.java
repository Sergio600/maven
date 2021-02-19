package com.sergio.service;

import com.sergio.domain.Order;
import com.sergio.domain.Product;
import com.sergio.domain.User;
import com.sergio.exception.InvalidArgumentException;
import com.sergio.repository.OrderRepository;
import com.sergio.repository.ProductRepository;
import com.sergio.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        Connection connection = SqlHelper.getConnection();
        Order order = new Order();
        try {
            PreparedStatement ps = connection.prepareStatement("" +
                    "SELECT ORDERS.ID, USER.ID, USER.LOGIN, ORDERS.TOTAL_PRICE \n" +
                    "FROM ORDERS \n" +
                    "INNER JOIN USER\n" +
                    "ON USER.ID=ORDERS.USER_ID\n" +
                    "WHERE ORDERS.USER_ID=?;");
            ps.setInt(1, user.getUserId());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                order.setId(rs.getInt(1));
                order.setUserID(rs.getInt(2));
                order.setTotalPrice(rs.getDouble(4));

                User user1 = new User();
                user1.setUserId(rs.getInt(2));
                user1.setName(rs.getString(3));

                order.setUser(user1);

            } else {
                order = new Order(user);
                order = OrderRepository.save(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }


    /**
     * Adds product to order.
     *
     * @param order            order id.
     * @param selectedProducts string array of product keys from product map.
     * @return order with saved order.
     */
    public static Order addProducts(Order order, String[] selectedProducts) {
        if (order == null || selectedProducts == null) {
            throw new InvalidArgumentException("Arguments cant be null");
        }

        String productTitle = selectedProducts[0];
        int productId = ProductRepository.getProductIdByTitle(productTitle);

        Product product = new Product();
        product.setId(productId);
        product.setName(productTitle);
        product.setPrice(ProductRepository.getAllProducts().get(productTitle));


        Connection connection = SqlHelper.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO ORDER_GOOD(ORDER_ID, GOOD_ID) VALUES (?,?)");
            ps.setInt(1, order.getId());
            ps.setInt(2, product.getId());
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        order.setProducts(ProductRepository.getProductsByOrder(order));
        order = OrderRepository.updateOrderTotalPrice(order);

        return order;
    }

    /**
     * Removes product from order
     *
     * @param order
     * @param idToRemoveProduct
     * @return order with removed product
     */
    public static Order removeProduct(Order order, int idToRemoveProduct) {
        if (order == null) {
            throw new InvalidArgumentException("Arguments cant be null");
        }

        Connection connection = SqlHelper.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("" +
                    "DELETE FROM ORDER_GOOD " +
                    "WHERE ORDER_ID=? AND GOOD_ID=?");
            ps.setInt(1, order.getId());
            ps.setInt(2, idToRemoveProduct);
            ps.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        order.setProducts(ProductRepository.getProductsByOrder(order));
        order = OrderRepository.updateOrderTotalPrice(order);

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
