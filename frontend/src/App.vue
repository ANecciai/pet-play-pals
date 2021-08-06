<template>
  <div id="app">
    <div id="nav">
      <h1> PET PLAY PALS</h1>
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{name: 'profile', params: {username: $store.state.user.username}}" v-if="$store.state.token != ''">Profile</router-link>
    </div>
    <router-view />
  </div>
</template>
<style>


#app{
  text-align: center;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
}

</style>
