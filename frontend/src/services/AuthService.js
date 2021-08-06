import axios from 'axios';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(userAndProfile) {
    return axios.post('/register', userAndProfile)
  },

 

}
