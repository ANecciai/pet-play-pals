<template>
  <div id="app">
    <div id="nav">
      <h1> PET PLAY PALS</h1>
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{name: 'profile', params: {username: $store.state.user.username}}" v-if="$store.state.token != ''">Profile</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{name:'search-pets'}">Search Pets</router-link>
    </div>
    <router-view />
  </div>
</template>
<style>


#app{
  text-align: center;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
}

h1,
li > a {
  text-transform: uppercase;
}

ul {
  padding: 0;
  margin: 0;
  display: flex;
  justify-content: center;
}

li {
  list-style: none;
}

li > a {
  margin: 20px;
}
 a {
   color: #7AB79A;
  font-size: .85em;
  text-decoration: none;
 }

 a:hover {
   color: black;
 }


</style>
