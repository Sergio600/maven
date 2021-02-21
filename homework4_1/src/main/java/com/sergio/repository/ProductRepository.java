package com.sergio.repository;

import com.sergio.domain.Order;
import com.sergio.domain.Product;
import com.sergio.sql.SqlHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {


    public static List<Product> getProductsByOrder(Order order) {
        List<Product> products = new ArrayList<>();
        Connection connection = SqlHelper.getConnection();
        ResultSet rs = null;
        try {
            PreparedStatement ps = connection.prepareStatement("" +
                    "SELECT GOOD.ID, GOOD.TITLE, GOOD.PRICE " +
                    "FROM ORDER_GOOD " +
                    "INNER JOIN GOOD " +
                    "ON ORDER_GOOD.GOOD_ID=GOOD.ID " +
                    "WHERE ORDER_GOOD.ORDER_ID=?");
            ps.setInt(1, order.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setPrice(rs.getDouble(3));

                products.add(product);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return products;
    }


    public static Map<String, Double> getAllProducts() {
        Map<String, Double> products = new HashMap<>();
        Connection connection = SqlHelper.getConnection();
        ResultSet rs = null;

        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery("" +
                    "Select * from good");
            while (rs.next()) {
                String productTitle = rs.getString("title");
                String productPrice = rs.getString("price");
                products.put(productTitle, Double.parseDouble(productPrice));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return products;
    }


    /**
     *
     * @param productTitle
     * @return
     */
    public static int getProductIdByTitle(String productTitle) {
        int productId = -1;
        Connection connection = SqlHelper.getConnection();
        ResultSet rs = null;

        try {
            PreparedStatement ps = connection.prepareStatement("" +
                    "Select * from good");
            rs = ps.executeQuery();
            while (rs.next()) {
                String productNameSql = rs.getString("title");
                if (productTitle.equals(productNameSql)) {
                    productId = rs.getInt("id");
                    break;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return productId;
    }
}
