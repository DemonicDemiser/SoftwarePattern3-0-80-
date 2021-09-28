package com.example.demo.services.implementation;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Scope //singleton default
public class UserServiceImpl implements UserService {

    Map<Integer, User> users = new HashMap<>(); //users would be accessed from everywhere

    @Override
    public boolean addUser(User user) {
        if(users.containsKey(user.getId())){
            return false;
        }
        users.put(user.getId(), user);
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        users.put(user.getId(), user);
        return true;
    }

    @Override
    public boolean deleteUser(User user) {
        users.remove(user.getId(), user);
        return true;
    }

    @Override
    public User read(int id) {
        return users.get(id);
    }

    @Override
    public boolean registerUser(User user) {
        if(users.containsKey(user.getEmail()) || users.containsKey(user.getId())){
            return false;
        }
        users.put(user.getId(), user);
        return true;
    }

    @Override
    public boolean loginUser(User user) {
        return false;
    }

    @Override
    public boolean logoutUser(User user) {
        return false;
    }
}
