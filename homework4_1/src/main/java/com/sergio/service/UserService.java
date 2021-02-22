package com.sergio.service;

import com.sergio.domain.User;
import com.sergio.exception.InvalidArgumentException;
import com.sergio.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public static User createOrGetUser(String userName) {
        if (userName == null) {
            throw new InvalidArgumentException("Name can't be null");
        }
        User user = UserRepository.getUser(userName);
        return user;
    }
}