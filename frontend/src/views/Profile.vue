<template>
  <div>
    <h2>WELCOME BACK, {{ $store.state.user.username.toUpperCase() }}!</h2>
    <h3>YOUR PETS</h3>
    <div v-for="pet in $store.state.pets" v-bind:key="pet.id">
      <router-link
        v-bind:to="{ name: 'pet-details', params: { petId: pet.petId } }"
        >{{ pet.name }}&nbsp;|&nbsp;{{ pet.species }}</router-link
      >
      <a id="delete-button" href="#" v-on:click="deletePet(pet.petId)"
        ><button id="delete-button">DELETE</button></a
      >
    </div>
    <router-link tag="button" v-bind:to="{ name: 'pet' }"> ADD PET</router-link>
    <h3>YOUR PLAYDATES</h3>
    <div
      v-for="playdate in $store.state.playdates"
      v-bind:key="playdate.playdateId"
    >
      <router-link
        v-bind:to="{
          name: 'playdate-details',
          params: { playdateId: playdate.playDateId },
        }"
      >
        {{ playdate.playdateDate }} &nbsp;|&nbsp; Time:
        {{ formatTime(playdate.playdateTime) }} &nbsp;|&nbsp; Host:
        {{ playdate.hostUsername }} &nbsp;|&nbsp; Invitee:
        {{ playdate.invitedUsername }}</router-link
      >
      <button
        v-if="playdate.statusType == 'Pending'"
        v-on:click.prevent="accept(playdate.playDateId)"
        value="Accept"
      >
        Accept
      </button>
      <button
        v-if="playdate.statusType == 'Pending'"
        v-on:click.prevent="decline(playdate.playDateId)"
        value="Decline"
      >
        Decline
      </button>
      <button
        v-if="playdate.statusType == 'Accepted'"
        v-on:click.prevent="cancel(playdate.playDateId)"
        value="Cancel"
      >
        Cancel
      </button>
    </div>
  </div>
</template>

<script>
import profileservice from "@/services/ProfileService";
import petservice from "@/services/PetService";
import playdateService from "@/services/PlaydateService";

export default {
  name: "profile",

  methods: {
    retrieveUser() {
      profileservice.profile(this.profile);
    },
    deletePet(petId) {
      if (
        confirm(
          "Are you sure you want to delete this pet? This action cannot be undone"
        )
      ) {
        petservice.deletePet(petId).then((response) => {
          if (response.status === 200) {
            alert("Pet successfully deleted");
            this.$store.commit("DELETE_PET", petId);
          }
        });
      }
    },

    formatTime(playdateTime) {

            let hours24 = parseInt(playdateTime.substring(0,2));
            let hours = ((hours24 + 11) % 12) + 1;
            let amPm = hours24 > 11 ? 'pm' : 'am';
            let minutes = playdateTime.substring(2);
            return hours + minutes + amPm;

        }
  },
  created() {
    petservice.getMyPets().then((response) => {
      if (response.status == 200) {
        this.$store.commit("SET_PETS", response.data);
      }
    }),
      playdateService.getMyPlaydates().then((response) => {
        if (response.status == 200) {
          this.$store.commit("SET_PLAYDATES", response.data);
        }
      });
  },
};
</script>

<style>
h3 {
  text-decoration: underline;
}
#delete-button {
  padding-top: 2px;
  padding-bottom: 2px;
  padding-left: 5px;
  padding-right: 5px;
}
</style>