<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h2 class="h3 mb-3 font-weight-normal">Create Account</h2>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div>
      <label for="username" class="sr-only">Username</label>
      </div>
      <div>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      </div>
      <div>
      <label for="password" class="sr-only">Password</label>
      </div>
      <div>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      </div>
      <div>
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      </div>

        <div>
          <h2>Create Profile</h2>
          <div>
            <label>First Name</label>
          </div>
          <div>
            <input placeholder="First Name" v-model="profile.firstName" type="text"/>
          </div>
          <div>
          <label>Last Name</label>
          </div>
          <div>
          <input placeholder="Last Name"  type="text" v-model="profile.lastName"/>
          </div>
          <div>
          <label>Zip Code</label>
          </div>
          <div>
          <input placeholder="Zip Code" type="text" v-model="profile.zipcode"/>
          </div>
          <div>
          <label>About Me</label>
          </div>
          <div>
          <input placeholder="About Me" type="textarea" v-model="profile.aboutMe"/>
          </div>
      </div>

      <div>
      <router-link :to="{ name: 'login' }">Already have an account?</router-link>
      </div>
      <div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        CREATE ACCOUNT
      </button>
      </div>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      profile:{
            firstName: '',
            lastName: '',
            zipcode: '',
            aboutMe: ''
            },
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register({user:this.user, profile:this.profile})
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style>

</style>
