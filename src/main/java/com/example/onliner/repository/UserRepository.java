package com.example.onliner.repository;

import com.example.onliner.domain.User;

import java.util.Optional;

public interface UserRepository {

    long create(User user);
    Optional<User> findByUsername(String username);
}
