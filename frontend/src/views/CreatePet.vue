<template>
  <div>
    <form v-on:submit.prevent="createPet">
        <div>
      <label>Pet Name</label>
        </div>
        <div>
      <input v-model="pet.name" placeholder="Pet Name" type="text" />
      </div>
      <div>
      <label>Species</label>
      </div>
      <div>
      <select type="text" placeholder="Species" v-model="pet.species">
        <option value = "Choose Pet Species">Pet Species</option>
        <option value = "Dog" > Dog </option>
        <option value = "Cat"> Cat </option>
        <option value = "Bird"> Bird </option>
      </select>
      </div>
      <div>
      <label>Breed</label>
      </div>
      <div>
      <input type="text" placeholder="Breed" v-model="pet.breed" />
      </div>
      <div>
      <label>Activity Level</label>
      </div>
      <div>
      <select v-model="pet.levelOfActivity">
          <option value="Choose Activity Level">Activity Level</option>
          <option value="Low">Low</option>
          <option value="Medium">Medium</option>
          <option value="High">High</option>
          </select>
      </div>
      <div>
      <label>Gender</label>
      </div>
      <div>
      <select v-model="pet.gender">
          <option value="Gender">Gender</option>
          <option value="Male">Male</option>
          <option value="Female">Female</option>
          </select>
      </div>
      <div>
      <label>Age</label>
      </div>
      <div>
      <input placeholder="Age" type="text" v-model="pet.age" />
      </div>
      <div>
      <label>Description</label>
      </div>
      <div>
      <input placeholder = "Description" type="textarea" v-model="pet.description"
      />
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