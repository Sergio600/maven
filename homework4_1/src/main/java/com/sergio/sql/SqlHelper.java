package com.sergio.sql;
import com.sergio.domain.Order;
import com.sergio.domain.User;

import java.sql.*;

public class SqlHelper {

    private static Connection connection = getConnection();

    public static Connection getConnection(){
        try {
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection(
                    "jdbc:h2:tcp://localhost/~/test;",
                    "sergio",
                    "12345");

        } catch (SQLException |ClassNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException("das");
        }
    }


    public static void addUser(User user){
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO USER(ID, LOGIN) VALUES (?,?)");
            ps.setInt(1, user.getUserId());
            ps.setString(2, user.getName());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateOrderTotalPrice(Order order){
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE ORDERS SET TOTAL_PRICE =? WHERE ID =?");
            ps.setDouble(1, order.getTotalPrice());
            ps.setInt(2, order.getId());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addNewOrder(Order order){
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO ORDERS(ID, USER_ID, TOTAL_PRICE) VALUES (?,?,?)");
            ps.setInt(1, order.getId());
            ps.setInt(2, order.getUser().getUserId());
            ps.setDouble(3, order.getTotalPrice());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void findUser(){
//        try {
//            PreparedStatement ps = connection.prepareStatement("INSERT INTO ORDERS(ID, USER_ID, TOTAL_PRICE) VALUES (?,?,?)");
//            ps.setInt(1, order.getId());
//            ps.setInt(2, order.getUser().getUserId());
//            ps.setDouble(3, order.getTotalPrice());
//            ps.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }





}