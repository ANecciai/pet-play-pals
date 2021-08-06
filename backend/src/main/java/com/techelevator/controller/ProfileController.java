package com.techelevator.controller;

import com.techelevator.dao.ProfileDao;
import com.techelevator.model.Profile;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
public class ProfileController {
    private final
    ProfileDao profileDao;

    public ProfileController(ProfileDao profileDao){
        this.profileDao = profileDao;
    }

    @RequestMapping(value = "/profiles", method = RequestMethod.GET)
    public List<Profile> getAllProfiles(){
        return profileDao.getAllProfiles();
    }

    @RequestMapping(value = "/profiles/{id}", method = RequestMethod.GET)
    public Profile getProfileById(@PathVariable  int profileId){
        return profileDao.getProfileById(profileId);
    }

    @RequestMapping(value = "/profiles/{username}", method = RequestMethod.GET)
    public Profile getProfileByUsername(@PathVariable String username){
        return profileDao.getProfileByUsername(username);
    }

    @RequestMapping(value = "/profiles/zipcode/{zipcode}", method = RequestMethod.GET)
    public Profile getProfileByZipcode(@PathVariable int zipcode){
        return profileDao.getProfileByZipcode(zipcode);
    }

    @RequestMapping(value="/profile/create", method = RequestMethod.POST)
    public void createProfile(@RequestBody Profile profile){
        profileDao.createProfile(profile);
    }

    @RequestMapping(value = "/profile/delete", method = RequestMethod.DELETE)
    public void deleteProfile(@RequestBody Principal principal){
        String currentUser = principal.getName();
        profileDao.deleteProfile(currentUser);
    }

    @RequestMapping(value = "/profile/update", method = RequestMethod.PUT)
    public void updateProfile(@RequestBody Profile profile, Principal principal){
        String currentUser = principal.getName();
        profileDao.updateProfile(profile, currentUser);

    }


}
