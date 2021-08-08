<template>
  <div>
    <form v-on:submit.prevent="createPlaydate">
      <div>
        <label
          >Address</label>
      </div>
      <div>
          <input
            v-model="playdate.address"
            placeholder="Address"
            type="text"
        />
      </div>
      <div>
        <label
          >City</label>
      </div>
      <div>
          <input v-model="playdate.city" placeholder="City" type="text"
        />
      </div>
      <div>
        <label
          >State</label>
      </div>
      <div>
          <input v-model="playdate.state" placeholder="State" type="text"
        />
      </div>
      <div>
        <label
          >Zip Code</label></div>
          <div>
          <input
            v-model="playdate.zipCode"
            placeholder="Zip Code"
            type="text"
        />
      </div>
      <div>
        <label>Date</label>
      </div>
      <div>
      <input v-model="playdate.playdateDate" placeholder="date" type="date"/>
      </div>
      <div>
        <label>Time</label>
      </div>
      <div>
          <input v-model="playdate.playdateTime" placeholder="time" type="time"
        />
      </div>
      <div>
        <label>Description</label>
      </div>
      <div>
          <input v-model="playdate.playdateDescription" placeholder="Description" type="textarea"
        />
      </div>
      <div>
        <input id="button" type="submit" value="SCHEDULE PLAYDATE" />
      </div>
    </form>
  </div>
</template>

<script>
import playdateService from "@/services/PlaydateService";

export default {
  name: "create-playdate",

  data() {
    return {
      playdate: {
        address: "",
        city: "",
        state: "",
        zipCode: "",
        playdateDate: "",
        playdateTime: "",
        status: "",
      },
    };
  },
  methods: {
    createPlaydate() {
      playdateService.createPlaydate(this.playdate);
    },
  },
};
</script>

<style>
#button{
   font-family: Verdana, Geneva, Tahoma, sans-serif;
  background-color: black;
  color:white;
  margin:12px;
  border-radius: 5px;
  padding-top:10px;
  padding-bottom:10px;
  padding-left: 40px;
  padding-right: 40px;
}
</style>