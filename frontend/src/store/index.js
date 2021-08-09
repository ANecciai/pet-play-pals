import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import { use } from 'vue/types/umd'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));
let currentPet = {}; 
let userPets=[]; 
currentPet = JSON.parse(localStorage.getItem('pet'));
userPets = JSON.parse(localStorage.getItem('pets'))

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    pet: currentPet,
    pets: userPets,
    
    

  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_PET (state, pet){
      state.pets = pets;
      if (pets[0]){
        state.pet = pets[0]
      }
      localStorage.setItem('pet', JSON.stringify(pet))
    }
  }
})
