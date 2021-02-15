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
                String productTitle = rs.getString("title");
                String productPrice = rs.getString("title");
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
        }

//		products.put("Book", 30.0);
//		products.put("TV", 500.0);
//		products.put("Car", 40000.0);
//		products.put("Mobile phone", 1000.0);
//		products.put("Shoes", 100.0);
        return products;
    }

    public static Map<String, Double> getPRODUCTS() {
        return PRODUCTS;
    }
}
