package com.sergio.repository;

import com.sergio.domain.Order;
import com.sergio.domain.User;
import com.sergio.sql.SqlHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    private static List<User> users = Collections.synchronizedList(new ArrayList<>());

    private UserRepository() {
    }

    /**
     * Adds user to users, and to Data Base
     *
     * @param user
     * @return
     */
    public static User save(User user) {
        users.add(user);
        OrderRepository.save(new Order(user));
        SqlHelper.addUser(user);
        return user;
    }

    public static List<User> getUsers() {
        return users;
    }

    /**
     * returns order choosed by name of customer
     *
     * @param customer
     * @return
     */
    public static Optional<Object> getByName(String customer) {
        for (User user : users) {
            if (user.getName().equals(customer)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
