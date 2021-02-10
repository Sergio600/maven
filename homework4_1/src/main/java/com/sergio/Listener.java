package com.sergio;

import com.sergio.sql.SqlHelper;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Hello Listener is works!");
        SqlHelper.initDB();

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
