import axios from 'axios';

export default {

profile(user){
    return axios.get(`/profiles/`, user)
  },
  
  editProfile(user){
    return axios.put(`/profiles/`, user)
  },

  viewUserProfile(username){
    return axios.get(`/profiles/details/${username}`)
  },
}