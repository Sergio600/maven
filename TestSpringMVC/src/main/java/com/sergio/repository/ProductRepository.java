package com.sergio.repository;

import com.sergio.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepository {

    @Autowired
    Connection connection;

    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        ResultSet rs = null;

        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery("" +
                    "Select * from good");
            while (rs.next()) {
                int productId = rs.getInt("id");
                String productTitle = rs.getString("title");
                double productPrice = rs.getDouble("price");
                products.add(new Product(productId, productTitle, productPrice));
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
    public int getProductIdByTitle(String productTitle) {
        int productId = -1;
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

    public Product getProductByID(int id) {
        Product product = new Product();
        ResultSet rs = null;

        try {
            PreparedStatement ps = connection.prepareStatement("" +
                    "Select * from good");
            rs = ps.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("id");
                if (id==productId) {
                    product.setId(id);
                    product.setTitle(rs.getString("TITLE"));
                    product.setPrice(rs.getDouble("PRICE"));
                    break;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return product;
    }

}
