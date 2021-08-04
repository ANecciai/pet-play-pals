package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@Component
@RestController
public class UserController {
    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    public User getUserByUsername(@PathVariable String username){
        return userDao.findByUsername(username);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Long userId){
        return userDao.getUserById(userId);
    }

    @RequestMapping(value = "users/{zip}", method = RequestMethod.GET)
    public List<User> getUsersByZip(@PathVariable int zip){
        return userDao.findByZip(zip);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "users", method = RequestMethod.DELETE)
    public void deleteUserAsAdmin(Long userId){
        userDao.deleteUserAsAdmin(userId);
    }

    @RequestMapping(value = "createuser", method = RequestMethod.POST)
    public void createUser(String username, String password, String role){
        userDao.create(username, password, role);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "users/{userId}/update", method = RequestMethod.PUT)
    public void updateUserAsAdmin(User user, @PathVariable Long userId){
        userDao.updateUserAsAdmin(user, userId);
    }

    @RequestMapping(value = "users/update", method = RequestMethod.PUT)
    public void updateUser(Principal currentUser, User user){
            userDao.updateUser(currentUser, user);
    }


}

