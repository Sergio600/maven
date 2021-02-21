package com.sergio.service;

import com.sergio.domain.User;
import com.sergio.exception.InvalidArgumentException;
import com.sergio.repository.UserRepository;
import com.sergio.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserService {

    public static User createOrGetUser(String userName) {

        if (userName == null) {
            throw new InvalidArgumentException("Name can't be null");
        }

        // убрать в юзеррепозитори .get()
        Connection connection = SqlHelper.getConnection();
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
                user = UserRepository.save(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}