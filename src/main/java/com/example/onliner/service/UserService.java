package com.example.onliner.service;

import com.example.onliner.domain.User;

public interface UserService {
    void create(User user);
    User retrieveByUsername(String username);
}
