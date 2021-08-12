<template>
  <div class="home">
    <header id="home-header">
      <h2>A mobile pet play date scheduling app</h2>
      <p>Search for pets in your area and join Pet Play Pals to create a profile, add your pets, and schedule a play date!</p>
    </header>
    <div>
      <img v-bind:src="picture.message" id="random-img"/> 
    </div>
    <div>
    <router-link :to="{ name: 'login' }" tag = button id="button" v-if="$store.state.token == ''">LOGIN</router-link>
    </div>
    <div>
    <router-link :to="{name: 'register'}" tag = button id="button" v-if="$store.state.token == ''">REGISTER</router-link>
    </div>
  </div>
</template>

<script>
import RandomPicService from "../services/RandomPicService"


export default {

  name: "home",
  data() {
    return {
      picture: ""
    }
  },
  created() {
    RandomPicService.picture().then(response => {
      this.picture = response.data
    });
  }
};
</script>
<style>


#home-header {
  max-width: 400px;
  margin: auto;
}

h2 {
  text-transform: uppercase;
}

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

#random-img {
  max-width: 500px;
  border:5px solid #974D21;
  outline: 5px solid black;
}

@media (max-width: 600px) {
  #random-img {
    max-width: 80%;
  }
}



</style>