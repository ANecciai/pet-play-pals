<template>
<div>
    <h1> PROFILE </h1>
    <h2>WELCOME BACK </h2>
    <router-link tag = button v-bind:to="{name: 'pet'}"> ADD PET</router-link>
    <router-link tag = button v-bind:to="{name: 'playdate'}">ADD PLAYDATE</router-link>
    </div>
  
</template>

<script>
import profileservice from "@/services/ProfileService";


export default {
    name: "profile",
   
   methods: {
       retrieveUser(){
           profileservice.profile(this.profile)
       }
   }

}
</script>

<style>

</style>