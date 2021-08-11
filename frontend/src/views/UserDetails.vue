<template>
  <div class="user-profile">
      <h1>{{profile.username}}</h1>
      <p>Name: {{profile.firstName}} {{profile.lastName}}</p>
      <p>Location: {{profile.zipCode}}</p>
      <p>About Me: {{profile.aboutMe}}</p>
  </div>
</template>

<script>
import profileService from "@/services/ProfileService";

export default {
    name:"user-details",
    data(){
        return{
            profile: {
                username: "",
                firstName: "",
                lastName: "",
                zipcode: "",
                aboutMe: "",

            }
        }
    },

    created(){
        profileService.viewUserProfile(this.$route.params.username).then(response => {
            this.profile = response.data;
        })
    }

}
</script>

<style>

</style>