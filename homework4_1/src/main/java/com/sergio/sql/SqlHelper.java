package com.sergio.sql;
import com.sergio.domain.Order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class SqlHelper {

    private static Connection connection = getConnection();

    public static Connection getConnection(){
        try {
            //создается новая база с именем указанным в url test1
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


    public static void updateOrderTotalPrice(Order order){
//        Connection conn = getConnection();
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
//        Connection conn = getConnection();
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






//    public static void initDB() {
//        System.out.println("init database");
//        try {
//            PreparedStatement ps = connection.prepareStatement("" +
//                    "insert into good (title,price)" +
//                    "values('test',100)");
//
//            System.out.println("new good is added to table good");
//            ps.execute();
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
}