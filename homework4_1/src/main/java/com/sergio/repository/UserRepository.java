package com.sergio.repository;

import com.sergio.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {
    @Autowired
    Connection connection;

    /**
     * Create or get user from DB
     *
     * @param userName
     * @return
     */
    public User getUser(String userName) {
        User user = new User();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * from user where LOGIN=?;");
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user.setUserId(rs.getInt("ID"));
                user.setName(userName);
            } else {
                user = new User(userName);
                user = save(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Adds user to users and to Data Base
     *
     * @param user
     * @return user with id from DB
     */
    public User save(User user) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO USER(LOGIN) VALUES (?)");
            ps.setString(1, user.getName());
            ps.execute();

            ps = connection.prepareStatement("SELECT * from user where LOGIN=?;");
            ps.setString(1, user.getName());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user.setUserId(rs.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }
}
