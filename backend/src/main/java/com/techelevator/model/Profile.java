package com.techelevator.model;

public class Profile {

    private int profileId;
    private String username;
    private String firstName;
    private String lastName;
    private int zipcode;

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
       this.profileId = profileId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
}
