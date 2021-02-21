package com.sergio.sql;

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


            // вынести данные для соединения в проперти файл
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
                    "TOTAL_PRICE double, " +
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

//    public static void showUsers(){
//        try {
//            PreparedStatement ps = connection.prepareStatement("Select * from user;");
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()){
//                System.out.println("USER: ");
//                System.out.print(rs.getInt(1)+ " ");
//                System.out.print(rs.getString(2)+ " ");
//                System.out.println();
//                System.out.println("------------------------");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void showOrders(){
//        try {
//            PreparedStatement ps = connection.prepareStatement("Select * from orders;");
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()){
//                System.out.println("ORDER: ");
//                System.out.print(rs.getInt(1)+ " ");
//                System.out.print(rs.getInt(2)+ " ");
//                System.out.print(rs.getDouble(3)+ " ");
//                System.out.println();
//                System.out.println("------------------------");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void showOrdersGood(){
//        try {
//            PreparedStatement ps = connection.prepareStatement("Select * from order_good;");
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()){
//                System.out.print("Order_good: ");
//                System.out.print(rs.getInt(1) + " ");
//                System.out.print(rs.getInt(2)+ " ");
//                System.out.print(rs.getInt(3)+ " ");
//                System.out.println();
//                System.out.println("------------------------");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}