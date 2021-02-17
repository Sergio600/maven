package com.sergio.domain;

import com.sergio.sql.SqlHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


/**
 * Map of products.
 */
public class PriceList {

    private static final Map<String, Double> PRODUCTS = initMap();

    private PriceList() {
    }

    private static Map<String, Double> initMap() {
        Map<String, Double> products = new HashMap<>();

        Connection connection = SqlHelper.getConnection();
        ResultSet rs=null;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery("" +
                    "Select * from good");
            while (rs.next()) {
//                String productID = rs.getString("title");
                String productTitle = rs.getString("title");
                String productPrice = rs.getString("price");
                products.put(productTitle, Double.parseDouble(productPrice));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return products;
    }

    public static Map<String, Double> getPRODUCTS() {
        return PRODUCTS;
    }
}
