package com.techelevator.dao;

import com.techelevator.model.Pet;

import java.security.Principal;
import java.util.List;

public interface PetDao {

    Pet getPetById(int petId);

    List<Pet> listAllPets();

    List<Pet> getPetByUsername(String username);

    List<Pet> listAllPetsBySpecies(String species);

    void createPet(Pet pet);

    void updatePet(Pet pet, int petId, Principal currentUser);

    void deletePet(int petId);
}
