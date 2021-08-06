package com.techelevator.dao;

import com.techelevator.model.Pet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPetDao implements PetDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcPetDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Pet getPetById(int petId) {
        String sql = "SELECT * FROM pet WHERE pet_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, petId);
        if(results.next()) {
            return mapRowToPet(results);
        } else {
            throw new RuntimeException("petId" + petId + "was not found.");
        }
    }

    @Override
    public Pet getPetByUsername(String username) {
        String sql = "SELECT * FROM pet WHERE username = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        if(results.next()) {
            return mapRowToPet(results);
        } else {
            throw new RuntimeException("username" + username + "was not found.");
        }
    }

    @Override
    public List<Pet> listAllPets() {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM pet";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Pet pet = mapRowToPet(results);
            pets.add(pet);
        }
        return pets;
    }

    @Override
    public List<Pet> listAllPetsBySpecies(String species) {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM pet WHERE species = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, species);
        while(results.next()) {
            Pet pet = mapRowToPet(results);
            pets.add(pet);
        }
        return pets;
    }

    @Override
    public void createPet(Pet pet) {
        String insertPet = "INSERT INTO pet (pet_name, username, species, breed, activity_level, gender, age, description) values (?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(insertPet, pet.getName(), pet.getUsername(), pet.getSpecies(), pet.getBreed(), pet.getLevelOfActivity(), pet.getGender(), pet.getAge(), pet.getDescription());
    }

    @Override
    public void updatePet(Pet pet, int petId, Principal currentUser) {
        String sql = "UPDATE pet SET username = ?, pet_name = ?, " +
                "species = ?,  breed = ?, activity_level = ?," +
                " gender = ?, age = ?, description = ? " +
                "WHERE pet_id = ? AND username = ?";
        jdbcTemplate.update(sql, currentUser.getName(), pet.getName(), pet.getSpecies(), pet.getBreed(), pet.getLevelOfActivity(), pet.getGender(), pet.getAge(), pet.getDescription(), petId, currentUser.getName());
    }

    @Override
    public void deletePet(int petId) {
        String deletePet = "DELETE FROM pet WHERE pet_id = ?";
        jdbcTemplate.update(deletePet, petId);
    }

    private Pet mapRowToPet(SqlRowSet rs) {
        Pet pet = new Pet();
        pet.setPetId(rs.getInt("pet_id"));
        pet.setUsername(rs.getString("username"));
        pet.setName(rs.getString("pet_name"));
        pet.setSpecies(rs.getString("species"));
        pet.setBreed(rs.getString("breed"));
        pet.setDescription(rs.getString("description"));
        pet.setLevelOfActivity(rs.getString("activity_level"));
        pet.setAge(rs.getInt("age"));
        pet.setGender(rs.getString("gender"));
        return pet;
    }
}
