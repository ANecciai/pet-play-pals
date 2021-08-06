<template>
  <div>
    <form v-on:submit.prevent="createPet">
      <label>Pet Name<input v-model="pet.name" type="text" /></label>
      <label>Species<input type="text" v-model="pet.species" /></label>
      <label>Breed<input type="text" v-model="pet.breed" /></label>
      <label
        >Activity Level<input type="text" v-model="pet.levelOfActivity"
      /></label>
      <label>Gender<input type="text" v-model="pet.gender" /></label>
      <label>Age<input type="text" v-model="pet.age" /></label>
      <label
        >Description<input type="textarea" v-model="pet.description"
      /></label>
      <input type="submit" value="SUBMIT" />
    </form>
  </div>
</template>

<script>
import petService from "@/services/PetService";

export default {
  name: "create-pet",
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
  methods: {
    createPet() {
      petService.createPet(this.pet);
    },
  },
};
</script>

<style>
</style>