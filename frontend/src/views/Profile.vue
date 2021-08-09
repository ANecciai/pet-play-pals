<template>
<div>
    <h1> PROFILE </h1>
    <h2>WELCOME BACK {{$store.state.user.username}}!! </h2>
    <p v-for="pet in $store.state.pets" v-bind:key="pet.id">{{pet.name}}&nbsp;|&nbsp;{{pet.species}}</p>
    <router-link tag = button v-bind:to="{name: 'pet'}"> ADD PET</router-link>
    <router-link tag = button v-bind:to="{name: 'playdate'}">ADD PLAYDATE</router-link>
    </div>
  
</template>

<script>
import profileservice from "@/services/ProfileService";
import petservice from "@/services/PetService";

export default {
    name: "profile",
   
   methods: {
       retrieveUser(){
           profileservice.profile(this.profile)
       }
   },
   created(){
   petservice.getPet().then(response => {
       if(response.status == 200){
           this.$store.commit("SET_PETS", response.data);
       }
   })
   },
  

   

}
</script>

<style>

</style>