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


    @RequestMapping(value = "/pets/species", method = RequestMethod.GET)
    public List<Pet> getPetsBySpecies(@RequestParam String species){
        return petDao.listAllPetsBySpecies(species);
    }

    //working

    @RequestMapping(value = "/pets/{id}", method = RequestMethod.GET)
    public Pet getPetByID(@PathVariable int id){
        return petDao.getPetById(id);
    }

    @RequestMapping(value = "/pets/zipcode/{zipcode}", method = RequestMethod.GET)
    public List<Pet> getPetsByZip(@PathVariable int zipcode){
        return petDao.getPetsByZipcode(zipcode);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/pets/username", method = RequestMethod.GET)
    public List<Pet> getPetByUsername(Principal principal){
        return petDao.getPetByUsername(principal.getName());
    }

    //working
    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/pets", method = RequestMethod.POST)
    public void createPet(@RequestBody Pet pet, Principal principal){
        pet.setUsername(principal.getName());
        petDao.createPet(pet);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/pets", method = RequestMethod.PUT)
    public void updatePet(Principal currentUser, @RequestBody Pet pet, int petId){
        petDao.updatePet(pet, petId, currentUser);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/pets/delete", method = RequestMethod.DELETE)
    public void deletePet(Principal principal, @RequestBody int petId){
        String currentUser = principal.getName();
        petDao.deletePet(currentUser, petId);
    }



    // ***** ADMIN COMMANDS *****

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin/delete/pets", method = RequestMethod.DELETE)
    public void deletePetAdmin(@RequestBody int PetId){
        petDao.deletePetAdmin(PetId);
    }


}
