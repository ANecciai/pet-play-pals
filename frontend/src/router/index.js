import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import Profile from '../views/Profile.vue'
import Pet from '../views/CreatePet.vue'
import CreatePlaydate from '../views/CreatePlayDate.vue'
import SearchPets from '../views/SearchPets.vue'
import PetDetails from '../views/PetDetails.vue'
import PlayDateDetails from '../views/PlayDateDetails.vue'
import UserDetails from '../views/UserDetails.vue'
//import { compile } from 'vue/types/umd' --- think this was added by mistake but not sure

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/profile/:username",
      name: "profile",
      component: Profile,
      meta: {
        requiresAuth: true
      }
    },
    {
    path: "/pet",
    name: "pet",
    component: Pet,
    meta:{
      requiresAuth: false
    }
    },
    {
    path: "/pets/:username",
    name: "pets",
    component: Pet,
    meta:{
      requiresAuth: false
    }
  },
    {
      path: "/playdate/:username",
      name: "playdate",
      component: CreatePlaydate,
      meta:{
        requiresAuth: true
      }
    },
    {
      path: "/searchpets",
      name:"search-pets",
      component: SearchPets,
      meta:{
        requiresAuth: false,
      }
    },
{
    path: "/pets/id/:petId",
    name: "pet-details",
    component: PetDetails,
    meta:{
      requiresAuth: false,
    }
  },
  {
  path: "/playdate/details/:playdateId",
  name: "playdate-details",
  component: PlayDateDetails,
  meta:{
    requiresAuth: true
  }
},{
path: "/user/details/:username",
name: "user-details",
component: UserDetails,
meta:{
  requiresAuth: false,
}
},
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
