package com.sergio.service;

import com.sergio.domain.User;
import com.sergio.exception.InvalidArgumentException;
import com.sergio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    public UserService(UserRepository userRepository){
//        this.userRepository=userRepository;
//    }

    public User createOrGetUser(String userName) {
        if (userName == null) {
            throw new InvalidArgumentException("Name can't be null");
        }
        User user = userRepository.getUser(userName);
        return user;
    }
}