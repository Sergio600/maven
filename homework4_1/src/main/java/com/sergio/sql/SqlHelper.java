package com.sergio.sql;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class SqlHelper {

    private static Connection connection = getConnection();

    public static Connection getConnection() {

        try {
            Properties properties = new Properties();
            InputStream is = new FileInputStream("DBconn.properties");
            properties.load(is);
            is.close();

            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            Class.forName("org.h2.Driver");
            return DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}