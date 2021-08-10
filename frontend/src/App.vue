<template>
  <div id="app">
    <header>
      <img src="@/images/logo.png" id="logo"/>
      <nav id="nav">
        <ul>
          <li><router-link v-bind:to="{ name: 'home' }">Home</router-link></li>
          <li><router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link></li>
          <li><router-link v-bind:to="{name: 'profile', params: {username: $store.state.user.username}}" v-if="$store.state.token != ''">Profile</router-link></li>
          <li><router-link v-bind:to="{name: 'search-pets'}">Search Pets</router-link></li>
        </ul>
      </nav>
    </header>
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
   color: #E37636;
  font-size: .85em;
  text-decoration: none;
 }

 a:hover {
   color: #974D21;
 }

 #logo {
   width: 300px;
 }


</style>
