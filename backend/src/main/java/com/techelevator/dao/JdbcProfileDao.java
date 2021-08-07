package com.techelevator.dao;

import com.techelevator.model.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.web.authentication.preauth.j2ee.J2eeBasedPreAuthenticatedWebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProfileDao implements ProfileDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcProfileDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Profile getProfileById(int profileId) {
        String sql = "SELECT * FROM profile WHERE profile_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, profileId);
        if(results.next()) {
            return mapRowToProfile(results);
        } else {
            throw new RuntimeException("profileId" + profileId + "was not found.");
        }
    }

    @Override
    public List<Profile> getAllProfiles() {
        List<Profile> profiles = new ArrayList<>();
        String sql = "SELECT * FROM profile";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Profile profile = mapRowToProfile(results);
            profiles.add(profile);
        }
        return profiles;
    }

    @Override
    public Profile getProfileByUsername(String username) {
        String sql = "SELECT * FROM profile WHERE username = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        if(results.next()) {
            return mapRowToProfile(results);
        } else {
            throw new RuntimeException("username" + username + "was not found.");
        }
    }

    @Override
    public Profile getProfileByFirstName(String firstName) {
        String sql = "SELECT * FROM profile WHERE first_name = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, firstName);
        if(results.next()) {
            return mapRowToProfile(results);
        } else {
            throw new RuntimeException("firstName" + firstName + "was not found.");
        }
    }

    @Override
    public Profile getProfileByLastName(String lastName) {
        String sql = "SELECT * FROM profile WHERE last_name = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, lastName);
        if(results.next()) {
            return mapRowToProfile(results);
        } else {
            throw new RuntimeException("lastName" + lastName + "was not found.");
        }
    }

    @Override
    public Profile getProfileByZipcode(int zipcode) {
        String sql = "SELECT * FROM profile WHERE zipcode = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, zipcode);
        if(results.next()) {
            return mapRowToProfile(results);
        } else {
            throw new RuntimeException("zipcode" + zipcode + "was not found.");
        }
    }

    @Override
    public void createProfile(Profile profile) {
        String insertProfile = "INSERT INTO profile (username, first_name, last_name, zipcode ,about_me) VALUES (?,?,?,?,?)";
       jdbcTemplate.update(insertProfile, profile.getUsername(), profile.getFirstName(), profile.getLastName(), profile.getZipcode(), profile.getAboutMe());
    }


    @Override
    public void updateProfile(Profile profile, String currentUser) {
        String sql = "UPDATE profile SET username = ?, first_name = ?," +
                " last_name = ?, zipcode = ?, about_me = ? WHERE username = ?";
        jdbcTemplate.update(sql, currentUser, profile.getFirstName(), profile.getLastName(), profile.getZipcode(), profile.getAboutMe(), currentUser);
    }

    @Override
    public void deleteProfile(String currentUser) {
        String deleteProfile = "DELETE FROM profile WHERE username = ?";
        jdbcTemplate.update(deleteProfile, currentUser);
    }

    private Profile mapRowToProfile(SqlRowSet rs) {
        Profile profile = new Profile();
        profile.setProfileId(rs.getInt("profile_id"));
        profile.setUsername(rs.getString("username"));
        profile.setFirstName(rs.getString("first_name"));
        profile.setLastName(rs.getString("last_name"));
        profile.setZipcode(rs.getInt("zipcode"));
        profile.setAboutMe(rs.getString("about_me"));
        return profile;
    }
}
