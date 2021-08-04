package com.techelevator.dao;

import com.techelevator.model.Pet;

import java.util.List;

public interface PetDao {

    Pet getPetById(int petId);

    List<Pet> listAllPets();

    Pet getPetByUsername(String username);

    List<Pet> listAllPetsBySpecies(String species);

    void createPet(String name, String username, String species, String gender);

    void updatePet(Pet pet, int petId);

    void deletePet(int petId);
}
