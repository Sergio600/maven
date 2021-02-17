
package com.sergio;

import com.sergio.sql.SqlHelper;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebListener
public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Hello Listener is works!");
        Connection connection = SqlHelper.getConnection();

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

    @Override
    public void contextDestroyed(ServletContextEvent sce) {


    }
}