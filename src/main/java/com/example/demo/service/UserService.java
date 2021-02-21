package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User validateAndSaveUser(User user);
    List<User> getUsers();
    Optional<User> getUser(long id);
}
