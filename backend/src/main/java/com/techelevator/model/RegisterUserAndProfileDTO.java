package com.techelevator.model;

import javax.validation.Valid;

public class RegisterUserAndProfileDTO {
    @Valid
    private RegisterUserDTO user;
    @Valid
    private Profile profile;

    public RegisterUserDTO getUser() {
        return user;
    }

    public void setUser(RegisterUserDTO registerUserDTO) {
        this.user = registerUserDTO;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
