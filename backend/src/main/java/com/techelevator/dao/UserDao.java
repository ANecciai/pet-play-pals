package com.techelevator.dao;

import com.techelevator.model.User;

import java.security.Principal;
import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(Long userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    List<User> findByZip(int zip);

    boolean create(String username, String password, String role);

    void updateUserAsAdmin(User user, Long userId);

    void updateUser(String currentUser, User user);

    void deleteUser(Principal currentUser);

    void deleteUserAsAdmin(Long userId);
}
