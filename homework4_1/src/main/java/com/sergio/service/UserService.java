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

        Connection connection = SqlHelper.getConnection();
        User user=null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * from user where name ='?'");
            ps.setString(1,userName);
            ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                user.setUserId(rs.getInt(1));
                user.setName(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (user.getName()!=null) {
            return UserRepository.getByName(userName);
        } else {
            user = new User(userName);
            return user;
        }
    }
}
