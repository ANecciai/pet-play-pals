package com.techelevator.dao;

import com.techelevator.model.User;

import java.security.Principal;
import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(Long userId);

    User findByUsername(String username);

    int findIdByUsername(String username);


    boolean create(String username, String password, String role);

    void updateUserAsAdmin(User user, Long userId);

    void updateUser(String currentUser, User user);

    void deleteUser(String currentUser);

    void deleteUserAsAdmin(Long userId);
}
