package com.example.onliner.service.impl;

import com.example.onliner.domain.User;
import com.example.onliner.repository.UserRepository;
import com.example.onliner.security.CustomAuthenticationException;
import com.example.onliner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) {
        userRepository.create(user);
    }

    @Override
    public User retrieveByUsername(String username) {
        Optional<User> optional = userRepository.findByUsername(username);
        if(optional.isEmpty()){
            throw new CustomAuthenticationException("Invalid login or password");
        }
        return optional.get();
    }
}
