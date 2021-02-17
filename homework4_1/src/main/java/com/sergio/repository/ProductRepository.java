package com.sergio.repository;

import com.sergio.domain.Product;
import com.sergio.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {


//    public static List<Product> getProductsById(int id) {
//        List<Product> products = new ArrayList<>();
//        Connection connection = SqlHelper.getConnection();
//        ResultSet rs = null;
//        Product product = null;
//
//        try {
//            PreparedStatement ps = connection.prepareStatement("" +
//                    "Select * from order_good where order_id = ?");
//            ps.setInt(1, id);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                product = new Product();
//                product.setId(rs.getInt("id"));
//                product.setId(rs.getInt("id"));
//                product.setId(rs.getInt("id"));
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } finally {
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//        }
//
//        return productId;
//    }


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
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return products;
    }


    /**
     *
     * @param productTitle
     * @return
     */
    public static int getIdByProductTitle(String productTitle) {
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
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        return productId;
    }
}
