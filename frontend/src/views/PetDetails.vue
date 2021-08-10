<template>
  <div class="pet-details">
      <h2> {{pet.name}}</h2>
      <p>{{pet.gender}}</p>
      <p>{{pet.age}}</p>
      <p>{{pet.species}}</p>
      <p>{{pet.breed}}</p>
      <p>{{pet.levelOfActivity}}</p>
      <p>{{pet.description}}</p>
      <router-link tag = button v-bind:to="{name: 'playdate'}">SCHEDULE PLAYDATE</router-link>
  </div>
</template>

<script>
import petService from "@/services/PetService"; 

export default {
  name: "pet-details",
  data() {
    return {
      pet: {
        name: "",
        species: "",
        breed: "",
        levelOfActivity: "",
        gender: "",
        age: "",
        description: "",
      },
    };
  },
  // use a method similar to get the user to for the schedule playdate
  created(){
    petService.getPetById(this.$route.params.petId).then(response =>{
      this.pet = response.data;
    })
  },

};
</script>

<style>

</style>