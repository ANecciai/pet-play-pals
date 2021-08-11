<template>
  <div class="pet-details">
      <h2>{{pet.name}}</h2>
      <img id="pet-image" v-if="pet.species == 'dog'" src="@/images/clipart 4.jpg"/>
      <img id="pet-image" v-if="pet.species == 'cat'" src="@/images/clipart 2.jpg"/>
      <img id="pet-image" v-if="pet.species == 'bird'" src="@/images/clipart 3.jpg"/>
      <p>Gender: {{pet.gender}}</p>
      <p>Age: {{pet.age}}</p>
      <p>Species: {{pet.species}}</p>
      <p>Breed: {{pet.breed}}</p>
      <p>Activity Level: {{pet.levelOfActivity}}</p>
      <p>Description: {{pet.description}}</p>
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
#pet-image{
  width: 200px;
  border-radius: 50%;
  border:3px solid black;
}

</style>