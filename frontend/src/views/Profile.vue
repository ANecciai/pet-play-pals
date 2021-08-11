<template>
<div >
    <h2>WELCOME BACK, {{$store.state.user.username.toUpperCase()}}! </h2>
    <h3> YOUR PETS </h3>
<<<<<<< HEAD
   <div v-for="pet in $store.state.pets" v-bind:key="pet.id">
   <router-link v-bind:to="{name: 'pet-details', params: {petId:pet.petId}}">{{pet.name}}&nbsp;|&nbsp;{{pet.species}}</router-link>
   <a id="button" href="#" v-on:click="deletePet(pet.petId)">Delete</a>
   </div>
=======
    <p v-for="pet in $store.state.pets" v-bind:key="pet.id">{{pet.name}}&nbsp;|&nbsp;{{pet.species}}</p>
     <router-link tag = button v-bind:to="{name: 'pet'}"> ADD PET</router-link>

>>>>>>> 735c9d21ebd72cc6e5c0b7396521f5d7e040edd9
    <h3> YOUR PLAYDATES </h3>
    <div v-for="playdate in $store.state.playdates" v-bind:key="playdate.playdateId">
        <router-link v-bind:to="{name:'playdate-details', params: {playdateId:playdate.playDateId}}"> {{playdate.playdateDate}} &nbsp;|&nbsp; Time: {{playdate.playdateTime}}  &nbsp;|&nbsp; Host: {{playdate.hostUsername}} &nbsp;|&nbsp; Invitee: {{playdate.invitedUsername}}</router-link>
        <button v-on:click="accept()" value="Accept">Accept</button>
        <button v-on:click="decline()" value="Decline">Decline</button>
        <button v-on:click="accept()" value="Accept">Cancel</button>
    </div>
    
   
    </div>
  
</template>

<script>
import profileservice from "@/services/ProfileService";
import petservice from "@/services/PetService";
import playdateService from "@/services/PlaydateService";
//import playdate from "@/services/PlaydateService";

export default {
    name: "profile",
   
   methods: {
       retrieveUser(){
           profileservice.profile(this.profile)
       }
   },
   created(){
   petservice.getMyPets().then(response => {
       if(response.status == 200){
           this.$store.commit("SET_PETS", response.data);
       }
   }),
   playdateService.getMyPlaydates().then(response => {
       if(response.status == 200){
           this.$store.commit("SET_PLAYDATES", response.data);
       }
   })
   },
   //playdates(){
    //   playdate.getPlaydate().then
   //}
   deletePet(id){
       petservice.deletePet(id).then(response =>{
           if (response.status === 200){
               this.getPet();
           }
       })
   }
  

   

}
</script>

<style>
h3{
    text-decoration:underline;
}

</style>