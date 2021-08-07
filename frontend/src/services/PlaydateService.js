import axios from 'axios';

export default {

createPlaydate(playdate){
    return axios.post(`/playdate/create`, playdate)
  },
  
  editPet(playdate, playdateId){
    return axios.put(`/playdate/edit` + playdateId, playdate)
  }
}