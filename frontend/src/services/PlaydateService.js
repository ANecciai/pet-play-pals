import axios from 'axios';

export default {

createPlaydate(playdate){
    return axios.post(`/playdate/create`, this.getAuthConfig(), playdate)
  },
  
  editPet(playdate, playdateId){
    return axios.put(`/playdate/edit` + playdateId, playdate)
  },


  getAuthConfig(){
    return {headers: {"Authorization": "JWT " + this.$store.state.token}};

  }
}