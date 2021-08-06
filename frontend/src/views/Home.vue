<template>
  <div class="home">
    <div>
    <router-link :to="{ name: 'login' }" tag = button id="button">&nbsp;LOGIN&nbsp;   </router-link>
    </div>
    <div>
    <router-link :to="{name: 'register'}" tag = button id="button">   &nbsp;REGISTER&nbsp;   </router-link>
    </div>
  </div>
</template>

<script>


import Login from "@/views/Login";
import Register from "@/views/Register";
import Login_register from '@/components/LoginRegister.vue';

export default {
  name: "home",

  component: {
    
    Login,
    Register,
    Login_register
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