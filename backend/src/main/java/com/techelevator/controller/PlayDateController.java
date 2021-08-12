package com.techelevator.controller;

import com.techelevator.dao.PlayDateDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.PlayDate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@CrossOrigin
@RestController
public class PlayDateController{
    private final UserDao userDao;
    private final PlayDateDao playDao;


    public PlayDateController(PlayDateDao playDao, UserDao userDao){
        this.playDao = playDao;
        this.userDao = userDao;
    }


    @RequestMapping(value = "/allplaydates", method = RequestMethod.GET)
    public List<PlayDate> getAllPlayDates(){
        return playDao.listAllPlayDates();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/playdates/{id}", method = RequestMethod.GET)
    public PlayDate getPlayDateById(@PathVariable int id){
        return playDao.getPlayDateByPlaydateId(id);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/myplaydates", method = RequestMethod.GET)
    public List<PlayDate> listMyPlayDates(Principal principal){
        String currentUser = principal.getName();
        return playDao.getAllPlayDatesByUsername(currentUser);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/playdates/pet/{id}", method = RequestMethod.GET)
    public List<PlayDate> listPlaydateByPet(@PathVariable int id){
        return playDao.getAllPlayDatesByPetId(id);
    }


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/playdates/create/{username}", method = RequestMethod.POST)
    public void createPlayDate(Principal principal, @RequestBody PlayDate playDate, @PathVariable String username){
        playDate.setHostUsername(principal.getName());
        playDate.setInvitedUsername(username);
        playDao.createPlayDate(playDate);
    }


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/playdates/update", method = RequestMethod.PUT)
    public void updatePlayDate(Principal principal, @RequestBody PlayDate playDate, @RequestBody int playDateId){
        String currentUser = principal.getName();
        if (currentUser == playDate.getHostUsername()){
            playDao.updatePlayDate(playDate, playDateId);
        }
    }


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/playdates/accept/{id}", method = RequestMethod.PUT)
    public void acceptPlayDate(Principal principal, @PathVariable int id){
        PlayDate playDate = playDao.getPlayDateByPlaydateId(id);
        String currentUser = principal.getName();
        if (currentUser.equals(playDate.getInvitedUsername())){
            playDao.acceptPlayDate(id);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/playdates/decline/{id}", method = RequestMethod.PUT)
    public void declinePlayDate(Principal principal, @PathVariable int id){
        PlayDate playDate = playDao.getPlayDateByPlaydateId(id);
        String currentUser = principal.getName();
        if (currentUser.equals(playDate.getInvitedUsername())){
            playDao.declinePlayDate(id);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/playdates/expire/{id}", method = RequestMethod.PUT)
    public void expirePlayDate(@PathVariable int id){
        PlayDate playDate = playDao.getPlayDateByPlaydateId(id);
        playDao.expirePlayDate(id);

    }



}

