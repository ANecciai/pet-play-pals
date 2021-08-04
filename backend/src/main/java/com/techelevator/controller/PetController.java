package com.techelevator.controller;

import com.techelevator.dao.PetDao;
import com.techelevator.model.Pet;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
public class PetController {
    private final PetDao petDao;


    public PetController(PetDao petDao) {
        this.petDao = petDao;
    }

    @RequestMapping(value = "/allpets", method = RequestMethod.GET)
    public List<Pet> getAllPets() {
        return petDao.listAllPets();
    }

    @RequestMapping(value = "/species", method = RequestMethod.GET)
    public List<Pet> getPetsBySpecies(@RequestParam String species){
        return petDao.listAllPetsBySpecies(species);
    }

    @RequestMapping(value = "/pets/{id}", method = RequestMethod.GET)
    public Pet getPetByID(int petId){
        return petDao.getPetById(petId);
    }

    @RequestMapping(value = "/pets/{username}", method = RequestMethod.GET)
    public Pet getPetByUsername(String username){
        return petDao.getPetByUsername(username);
    }

    @RequestMapping(value = "/pets", method = RequestMethod.POST)
    public void createPet(String name, String username, String species, String gender){
        petDao.createPet(name, username, species, gender);
    }

    @RequestMapping(value = "/pets", method = RequestMethod.PUT)
    public void updatePet(Pet pet, int petId, Principal currentUser){

    }



}
