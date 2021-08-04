package com.techelevator.dao;

import com.techelevator.model.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProfileDao implements ProfileDao {
    private JdbcTemplate jdbcTemplate;


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
    public void createProfile(String username, String firstName, String lastName, int zipcode) {
        String insertProfile = "INSERT INTO profile (username, first_name, last_name, zipcode) values (?,?,?,?)";
    }

    @Override
    public void updateProfile(Profile profile, int profileId, Principal currentUser) {
        String sql = "UPDATE profile SET username = ?, first_name = ?," +
                "last_name = ?, zipcode = ? WHERE profile_id = ?";
        jdbcTemplate.update(sql, currentUser.getName(), profile.getUsername(), profile.getFirstName(), profile.getLastName(), profile.getZipcode(), profileId);
    }

    @Override
    public void deleteProfile(int profileId) {
        String deleteProfile = "DELETE FROM profile WHERE profile_id = ?";
        jdbcTemplate.update(deleteProfile, profileId);
    }

    private Profile mapRowToProfile(SqlRowSet rs) {
        Profile profile = new Profile();
        profile.setProfileId(rs.getInt("profile_id"));
        profile.setFirstName(rs.getString("first_name"));
        profile.setLastName(rs.getString("last_name"));
        profile.setZipcode(rs.getInt("zipcode"));
        return profile;
    }
}
