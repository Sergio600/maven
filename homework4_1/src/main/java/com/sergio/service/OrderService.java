package com.sergio.service;

import com.sergio.domain.Order;
import com.sergio.domain.Product;
import com.sergio.domain.User;
import com.sergio.exception.InvalidArgumentException;
import com.sergio.repository.OrderRepository;
import com.sergio.repository.ProductRepository;
import com.sergio.repository.UserRepository;
import com.sergio.sql.SqlHelper;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

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
        Order order=null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * from orders where user_id ='?'");
            ps.setInt(1,user.getUserId());
            ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                order.setId(rs.getInt(1));
                order.getUser().setUserId(rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (order.getId()!=0) {
            return OrderRepository.getByUser(user);
        } else {
            order = new Order(user);
            return order;
        }
    }

    /**
     * Adds product to order.
     *
     * @param user               order id.
     * @param selectedProducts string array of product keys from product map.
     * @return order with saved order.
     */
    public static Order addProducts(User user, String[] selectedProducts) {
        if (user == null || selectedProducts == null) {
            throw new InvalidArgumentException("Arguments cant be null");
        }

        Order order = user.getOrder();
        List<Product> products = order.getProducts();
        String productTitle = selectedProducts[0];
        Product product = new Product();
        product.setName(productTitle);
        product.setPrice(ProductRepository.getAllProducts().get(productTitle));
        products.add(product);
        order.setProducts(products);
        order.setTotalPrice(calcTotalPrice(order));

        return order;
    }

    /**
     * Removes product from order
     *
     * @param user
     * @param index
     * @return order with removed product
     */
    public static Order removeProduct(User user, int index) {
        if (user == null) {
            throw new InvalidArgumentException("Arguments cant be null");
        }

        Order order = user.getOrder();
        List<Product> products = order.getProducts();
        products.remove(index);

        order.setProducts(products);
        order.setTotalPrice(calcTotalPrice(order));

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
