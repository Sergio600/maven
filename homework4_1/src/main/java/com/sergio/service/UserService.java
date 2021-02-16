package com.sergio.service;

import com.sergio.domain.User;
import com.sergio.exception.InvalidArgumentException;
import com.sergio.repository.UserRepository;

public class UserService {

    public static User createOrGetUser(String userName) {

        if (userName == null) {
            throw new InvalidArgumentException("Name can't be null");
        }

        if (UserRepository.getByName(userName).isPresent()) {
            return (User) UserRepository.getByName(userName).get();
        } else {
            User user = new User(userName);
            UserRepository.save(user);
            return user;
        }
    }
}
