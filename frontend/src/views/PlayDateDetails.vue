<template>
  <div class="playdate-details">
    <h2>Play Date Details</h2>
    <p>
      Host:
      <router-link
        v-bind:to="{
          name: 'user-details',
          params: { username: playdate.hostUsername },
        }"
        >{{ playdate.hostUsername }}</router-link
      >
    </p>
    <p>
      Invitee:
      <router-link
        v-bind:to="{
          name: 'user-details',
          params: { username: playdate.invitedUsername },
        }"
        >{{ playdate.invitedUsername }}</router-link
      >
    </p>
    <p>Date: {{ playdate.playdateDate }}</p>
    <p>Time: {{ playdate.playdateTime }}</p>
    <p>
      Location: {{ playdate.address }} {{ playdate.city }} {{ playdate.state }}
      {{ playdate.zipCode }}
    </p>
    <p>Status: {{ playdate.statusType }}</p>
    <div
      class="accept-decline"
      v-if="playdate.hostUsername != this.$store.state.user.username"
    >
      <button
        v-if="playdate.statusType == 'Pending'"
        v-on:click="accept(playdate.playDateId)"
        value="Accept"
      >
        Accept
      </button>
      <button
        v-if="playdate.statusType == 'Pending'"
        v-on:click="decline(playdate.playDateId)"
        value="Decline"
      >
        Decline
      </button>
    </div>
    <button
      v-if="
        playdate.statusType == 'Accepted' ||
        playdate.hostUsername == $store.state.user.username
      "
      v-on:click="cancel(playdate.playDateId)"
      value="Cancel"
    >
      Cancel
    </button>
  </div>
</template>

<script>
import playdateService from "@/services/PlaydateService";

export default {
  name: "playdate-details",
  data() {
    return {
      playdate: {
        address: "",
        city: "",
        state: "",
        zipCode: "",
        playdateTime: "",
        playdateDate: "",
        playdateDescription: "",
        hostUsername: "",
        invitedUsername: "",
        statusType: "",
      },
    };
  },
  methods: {
    accept(playdateId) {
      playdateService.acceptPlaydate(playdateId).then((response) => {
        if (response.status == 200) {
          this.$router.push(`/profile/${this.$store.state.user.username}`);
        }
      });
    },
    decline(playdateId) {
      playdateService.declinePlaydate(playdateId);
    },
    cancel(playdateId) {
      playdateService.cancelPlaydate(playdateId);
    },
  },
  created() {
    playdateService
      .getPlaydateById(this.$route.params.playdateId)
      .then((response) => {
        this.playdate = response.data;
      });
  },
};
</script>

<style>
</style>