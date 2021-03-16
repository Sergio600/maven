package com.sergio.repository;

import com.sergio.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {

//    @Autowired
//    Connection connection;

    private SessionFactory sessionFactory;

    /**
     * Adds user to users and to Data Base
     *
     * @param user
     * @return user with id from DB
     */
    public User save(User user) {
        sessionFactory.getCurrentSession().save(user);
        return user;

//        PreparedStatement ps = null;
//        try {
//            ps = connection.prepareStatement("INSERT INTO USER(LOGIN) VALUES (?)");
//            ps.setString(1, user.getLogin());
//            ps.execute();
//
//            ps = connection.prepareStatement("SELECT * from user where LOGIN=?;");
//            ps.setString(1, user.getLogin());
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                user.setId(rs.getInt(1));
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return user;
    }

    /**
     * Create or get user from DB
     *
     * @param userLogin
     * @return
     */
    public User getUser(String userLogin) {
        User user = new User();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * from user where LOGIN=?;");
            ps.setString(1, userLogin);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user.setId(rs.getInt("ID"));
                user.setLogin(userLogin);
            } else {
                user = new User(userLogin);
                user = save(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


}
