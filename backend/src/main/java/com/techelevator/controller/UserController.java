package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@CrossOrigin
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

    @RequestMapping(value = "users/zip/{zip}", method = RequestMethod.GET)
    public List<User> getUsersByZip(@PathVariable int zip){
        return userDao.findByZip(zip);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "users", method = RequestMethod.DELETE)
    public void deleteUserAsAdmin(Long userId){
        userDao.deleteUserAsAdmin(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "users/{userId}/update", method = RequestMethod.PUT)
    public void updateUserAsAdmin(User user, @PathVariable Long userId){
        userDao.updateUserAsAdmin(user, userId);
    }

    @RequestMapping(value = "users/update", method = RequestMethod.PUT)
    public void updateUser(Principal principal, User user){
        String currentUser = principal.getName();
            userDao.updateUser(currentUser, user);
    }




}

