<template>
  <div>
    <form v-on:submit.prevent="createPet">
        <div>
      <label>Pet Name<input v-model="pet.name" placeholder="Pet Name" type="text" /></label>
      </div>
      <div>
      <label>Species<input type="text" placeholder="Species" v-model="pet.species" /></label>
      </div>
      <div>
      <label>Breed<input type="text" placeholder="Breed" v-model="pet.breed" /></label>
      </div>
      <div>
      <label>Activity Level<select v-model="pet.levelOfActivity">
          <option value="Choose Activity Level">Activity Level</option>
          <option value="Low">Low</option>
          <option value="Medium">Medium</option>
          <option value="High">High</option>
          </select>
      </label>
      </div>
      <div>
      <label>Gender<select v-model="pet.gender">
          <option value="Gender">Gender</option>
          <option value="Male">Male</option>
          <option value="Female">Female</option>
          </select>
      </label>
      </div>
      <div>
      <label>Age<input placeholder="Age" type="text" v-model="pet.age" /></label>
      </div>
      <div>
      <label
        >Description<input placeholder = "Description" type="textarea" v-model="pet.description"
      /></label>
      </div>
      <div>
      <input id="button" type="submit" value="SAVE PET" />
      </div>
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
      petService
      .createPet(this.pet)
      .then(response => {
        if (response.status == 200){
          this.$router.push(`/profile/${this.$store.state.user.username}`);
        }
      })
      
      
    },
},
}
</script>

<style>
#button{
   font-family: Verdana, Geneva, Tahoma, sans-serif;
  background-color: black;
  color:white;
  margin:12px;
  border-radius: 5px;
  padding-top:10px;
  padding-bottom:10px;
  padding-left: 40px;
  padding-right: 40px;
}

</style>