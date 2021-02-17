package com.sergio.sql;
import com.sergio.domain.Order;
import com.sergio.domain.User;

import java.sql.*;

public class SqlHelper {

    private static Connection connection = getConnection();

    public static Connection getConnection(){
        try {
//            Class.forName("org.h2.Driver");
//            return DriverManager.getConnection(
//                    "jdbc:h2:tcp://localhost/~/test;",
//                    "sergio",
//                    "12345");

            Class.forName("org.h2.Driver");
            return DriverManager
                    .getConnection(
                            "jdbc:h2:mem:test1;DB_CLOSE_DELAY=-1;",
                            "sergio",
                            "12345");

        } catch (SQLException |ClassNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException("das");
        }
    }


    public static void initDB() {

        try {
            PreparedStatement ps = connection.prepareStatement("" +
                    "create table USER (" +
                    "ID INT auto_increment, " +
                    "LOGIN varchar(25) not null, " +
                    "PASSWORD varchar(25), " +
                    "primary key(id));" +
                    "" +
                    "create table ORDERS (" +
                    "ID INT auto_increment, " +
                    "USER_ID int not null, " +
                    "TOTAL_PRICE double not null, " +
                    "primary key(id));" +
                    "" +
                    "create table GOOD (" +
                    "ID INT auto_increment, " +
                    "TITLE varchar(25) not null, " +
                    "PRICE int not null, " +
                    "primary key(id));" +
                    "" +
                    "create table ORDER_GOOD (" +
                    "ID INT auto_increment, " +
                    "ORDER_ID int not null, " +
                    "GOOD_ID int not null, " +
                    "primary key(id));");

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement ps = connection.prepareStatement("" +
                    "INSERT INTO GOOD(TITLE, PRICE) " +
                    "VALUES ('Fanta', 2.0);" +
                    "INSERT INTO GOOD(TITLE, PRICE) " +
                    "VALUES ('Bread', 1.0);" +
                    "INSERT INTO GOOD(TITLE, PRICE) " +
                    "VALUES ('Shoes', 200.0);" +
                    "INSERT INTO GOOD(TITLE, PRICE) " +
                    "VALUES ('Phone',1000.0);" +
                    "INSERT INTO GOOD(TITLE, PRICE) " +
                    "VALUES ('car', 50000.0);" +
                    "");
            ps.execute();

//              Проверка создается ли база
//            ps=connection.prepareStatement("select * from good;");
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()){
//                System.out.println(rs.getInt(1));
//                System.out.println(rs.getString(2));
//                System.out.println(rs.getInt(3));
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void addUser(User user){

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