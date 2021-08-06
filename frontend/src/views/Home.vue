<template>
  <div class="home">
    <div>
    <router-link :to="{ name: 'login' }" tag = button id="button">LOGIN</router-link>
    </div>
    <div>
    <router-link :to="{name: 'register'}" tag = button id="button">REGISTER</router-link>
    </div>
  </div>
</template>

<script>




export default {
  name: "home",

  component: {
    
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