<template>
  <div class="pet-details">
      <h2> {{pet.name}}</h2>
      <p>{{pet.gender}}</p>
      <p>{{pet.age}}</p>
      <p>{{pet.species}}</p>
      <p>{{pet.breed}}</p>
      <p>{{pet.levelOfActivity}}</p>
      <p>{{pet.description}}</p>
  </div>
</template>

<script>
import petService from "@/services/PetService"; 
export default {
  name: "pet-details",
  computed:{
    pet(){
    return petService.getPetById(this.$store.state.activePetId)
  }
  },
  created(){
    const activePetId = this.$route.params.petId;
    this.$store.commit("SET_ACTIVE_PET",activePetId);   
         
    
  }

};
</script>

<style>

</style>