<template>
  <div class="playdate-details">
    <h2>Play Date Details</h2> 
    <p>Host: <router-link v-bind:to="{name: 'user-details', params:{username:playdate.hostUsername}}">{{playdate.hostUsername}}</router-link></p>
    <p>Invitee: <router-link v-bind:to="{name: 'user-details', params:{username:playdate.invitedUsername}}">{{playdate.invitedUsername}}</router-link></p>
    <p>Date: {{playdate.playdateDate}}</p>
    <p>Time: {{playdate.playdateTime}}</p>
    <p>Location: {{playdate.address}} {{playdate.city}} {{playdate.state}} {{playdate.zipCode}}</p> 
  </div>
</template>

<script>
import playdateService from "@/services/PlaydateService";

export default {
    name:"playdate-details",
    data(){
        return {
        playdate:{
            address: "",
            city: "",
            state: "",
            zipCode: "",
            playdateTime: "",
            playdateDate: "",
            playdateDescription: "",
            statusType: "",
            hostUsername: "",
            invitedUsername: ""
        }
        }
    },
    created(){
        playdateService.getPlaydateById(this.$route.params.playdateId).then(response => {
            this.playdate = response.data;
        })
    },

};
</script>

<style>

</style>