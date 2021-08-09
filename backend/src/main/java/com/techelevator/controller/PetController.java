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
    public Pet getPetByID(@PathVariable int petId){
        return petDao.getPetById(petId);
    }

    @RequestMapping(value = "/pets/username", method = RequestMethod.GET)
    public List<Pet> getPetByUsername(@RequestParam String username){
        return petDao.getPetByUsername(username);
    }

    @RequestMapping(value = "/pets", method = RequestMethod.POST)
    public void createPet(@RequestBody Pet pet, Principal principal){
        pet.setUsername(principal.getName());
        petDao.createPet(pet);
    }

    @RequestMapping(value = "/pets", method = RequestMethod.PUT)
    public void updatePet(@RequestBody Pet pet, int petId, Principal currentUser){
        petDao.updatePet(pet, petId, currentUser);
    }



}
