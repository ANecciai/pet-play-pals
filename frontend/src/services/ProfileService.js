import axios from 'axios';

export default {

profile(user){
    return axios.get(`/profiles/`, user)
  },
  
  editProfile(user){
    return axios.put(`/profiles/`, user)
  }
}