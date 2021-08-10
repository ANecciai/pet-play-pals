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
import SearchPets from '@/views/SearchPets';
export default {
  name: "pet-details",
  components:{ SearchPets },
  props: ["petId"],
  computed:{
    pet(){
    return petService.getPetById(this.petId)
  }
  },

};
</script>

<style>

</style>