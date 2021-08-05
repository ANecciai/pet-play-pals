package com.techelevator.dao;

import com.techelevator.model.Profile;

import java.security.Principal;
import java.util.List;

public interface ProfileDao {

    Profile getProfileById(int profileId);

    List<Profile> getAllProfiles();

    Profile getProfileByUsername(String username);

    Profile getProfileByFirstName(String firstName);

    Profile getProfileByLastName(String lastName);

    Profile getProfileByZipcode(int zipcode);

    void createProfile(Profile profile);

    void updateProfile(Profile profile, String currentUser);

    void deleteProfile(int profileId);
}
