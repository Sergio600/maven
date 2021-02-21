package com.sergio.repository;

import com.sergio.sql.SqlHelper;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ProductRepository {

    public static Map<String, Double> getProducts() {
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
