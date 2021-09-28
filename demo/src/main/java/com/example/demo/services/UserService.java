package com.example.demo.services;

import com.example.demo.models.User;

public interface UserService {
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);
    User read(int id);
    boolean registerUser(User user);
    boolean loginUser(User user);
    boolean logoutUser(User user);
}
