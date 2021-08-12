<template>
  <div class="playdate-details">
    <h2>Play Date Details</h2> 
    <p>Host: <router-link v-bind:to="{name: 'user-details', params: {username:playdate.hostUsername}}">{{playdate.hostUsername}}</router-link></p>
    <p>Invitee: <router-link v-bind:to="{name: 'user-details', params: {username:playdate.invitedUsername}}">{{playdate.invitedUsername}}</router-link></p>
    <p>Date: {{playdate.playdateDate}}</p>
    <p>Time: {{ formatTime() }}</p> 
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
    methods: {
        formatTime() {

            let hours24 = parseInt(this.playdate.playdateTime.substring(0,2));
            let hours = ((hours24 + 11) % 12) + 1;
            let amPm = hours24 > 11 ? 'pm' : 'am';
            let minutes = this.playdate.playdateTime.substring(2);
            return hours + minutes + amPm;

        }
    }
    
    
    


};
</script>

<style>

</style>