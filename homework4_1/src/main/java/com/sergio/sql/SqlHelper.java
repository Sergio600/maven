package com.sergio.sql;
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
                    "jdbc:h2:~/test;",
                    "sergio",
                    "12345");

        } catch (SQLException |ClassNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException("das");
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