<template>
  <div class="user-profile">
      <h2>{{profile.username}}</h2>
      <p>Name: {{profile.firstName}} {{profile.lastName}}</p>
      <p>Location: {{profile.zipcode}}</p>
      <p>About Me: {{profile.aboutMe}}</p>
      
      <div class="user-pets">
      <h3>{{profile.username}}'s Pets</h3>
      <p v-for="pet in pets" v-bind:key="pet.petId">
          <router-link v-bind:to="{name: 'pet-details', params: {petId:pet.petId}}">{{pet.name}}</router-link>
      </p>
      

      </div>
  </div>
</template>

<script>
import profileService from "@/services/ProfileService";
import petService from "@/services/PetService";

export default {
    name:"user-profile",
    data(){
        return{
            profile: {
                username: "",
                firstName: "",
                lastName: "",
                zipcode: "",
                aboutMe: "",
            },
            pets:{
            }
        }
    },

    created(){
        profileService.viewUserProfile(this.$route.params.username).then(response => {
            this.profile = response.data;
        }),
        petService.getPetsByUsername(this.$route.params.username).then(response => {
            this.pets = response.data;
        })
    }

}
</script>

<style>

</style>