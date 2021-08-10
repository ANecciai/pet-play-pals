<template>
  <div class="home">
    <p>Connect with pet owners nearby<br>and schedule playdates!</p>
    <div>
      
    </div>
    <div>
    <router-link :to="{ name: 'login' }" tag = button id="button">LOGIN</router-link>
    </div>
    <div>
    <router-link :to="{name: 'register'}" tag = button id="button">REGISTER</router-link>
    </div>
  </div>
</template>

<script>
import RandomPicService from "../services/RandomPicService"


export default {

  name: "home",
  data() {
    return {
      message: ""
    }
  },
  created() {
    RandomPicService.get().then(response => {
      this.message = response.data
    });
  }
};
</script>
<style>
@import url('https://fonts.googleapis.com/css2?family=Poiret+One&display=swap');


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
.home{
  text-align: center;
  color: black;
}


</style>