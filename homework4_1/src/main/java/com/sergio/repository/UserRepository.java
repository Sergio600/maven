package com.sergio.repository;

import com.sergio.domain.User;
import com.sergio.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRepository {

    /**
     * Adds user to users, and to Data Base
     *
     * @param user
     * @return
     */
    public static User save(User user) {
        Connection connection = SqlHelper.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO USER(LOGIN) VALUES (?)");
            ps.setString(1, user.getName());
            ps.execute();

            ps = connection.prepareStatement("SELECT * from user where LOGIN=?;");
            ps.setString(1, user.getName());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user.setUserId(rs.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }
}
