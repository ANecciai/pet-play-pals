<template>
<div>
    <h2>WELCOME BACK, {{$store.state.user.username.toUpperCase()}}! </h2>
    <h3> YOUR PETS </h3>
    <p v-for="pet in $store.state.pets" v-bind:key="pet.id">{{pet.name}}&nbsp;|&nbsp;{{pet.species}}</p>
    <h3> YOUR PLAYDATES </h3>
    <router-link tag = button v-bind:to="{name: 'pet'}"> ADD PET</router-link>
    </div>
  
</template>

<script>
import profileservice from "@/services/ProfileService";
import petservice from "@/services/PetService";
//import playdate from "@/services/PlaydateService";

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
   //playdates(){
    //   playdate.getPlaydate().then
   //}
  

   

}
</script>

<style>
h3{
    text-decoration:underline;
}

</style>