package com.techelevator.controller;

import com.techelevator.dao.PetDao;
import com.techelevator.dao.PlayDateDao;
import com.techelevator.dao.ProfileDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.PlayDate;
import com.techelevator.model.Profile;
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
    private final PetDao petDao;
    private final ProfileDao profileDao;
    private final PlayDateDao playDao;


    public UserController(UserDao userDao, PetDao petDao, ProfileDao profileDao, PlayDateDao playDao) {
        this.userDao = userDao;
        this.petDao = petDao;
        this.profileDao = profileDao;
        this.playDao = playDao;
    }

    //Working

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


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/users/delete", method = RequestMethod.DELETE)
    public void deleteOwnAccount(Principal principal){
        String currentUser = principal.getName();
        userDao.deleteUser(currentUser);
    }


    //Working
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/users/update", method = RequestMethod.PUT)
    public void updateUser(Principal principal, @RequestBody User user){
        String currentUser = principal.getName();
            userDao.updateUser(currentUser, user);
    }



    // ***** ADMIN COMMANDS *****

    //Probably won't use this because of the issues with password hashing
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/users/{userId}/update", method = RequestMethod.PUT)
    public void updateUserAsAdmin(@RequestBody User user, @PathVariable Long userId){
        userDao.updateUserAsAdmin(user, userId);
    }

    //Need to add in delete for pets and profile -- should we put them all in this command or call all three separately
    //on front end?
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin/delete/user", method = RequestMethod.DELETE)
    public void deleteUserAsAdmin(@RequestParam Long userId){
        userDao.deleteUserAsAdmin(userId);
        User toDelete = userDao.getUserById(userId);
        profileDao.deleteProfile(toDelete.getUsername());



    }



}

