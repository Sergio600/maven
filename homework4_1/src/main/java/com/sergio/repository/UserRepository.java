package com.sergio.repository;

import com.sergio.domain.User;
import com.sergio.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
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


//            System.out.println("Пользователь добавлен в базу!");
//
//            ps =connection.prepareStatement("Select * from user where login='sergio';");
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()){
//                System.out.println(rs.getInt(1));
//                System.out.println(rs.getString(2));
//                }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }


//    /**
//     * returns order choosed by name of customer
//     *
//     * @param userId
//     * @return
//     */
//    public static User getByID(int userId) {
//        Connection connection = SqlHelper.getConnection();
//        User user = null;
//        try {
//            PreparedStatement ps = connection.prepareStatement("SELECT * from user where id =?");
//            ps.setInt(1, userId);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                int userID = rs.getInt("ID");
//                String userName = rs.getString("LOGIN");
//                user = new User(userName);
//                user.setUserId(userID);
//
//            } else {
//                System.out.println("Пользователь не найден");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

////        user.setOrder(OrderRepository.getOrderByUserName(userName));
//
//        return user;
//    }
}
