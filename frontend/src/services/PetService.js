import axios from 'axios';

export default {

createPet(pet){
  
    return axios.post(`/pets/`, this.getAuthConfig(), pet)
  },
  
  editPet(pet, petId){
    return axios.put(`/pets/` + petId, this.getAuthConfig(), pet)
  },

  getPet(pet){
    return axios.get(`/pets/username` + username, this.getAuthConfig(), pet)
  },

  getAuthConfig(){
    return {headers: {"Authorization": "JWT " + this.$store.state.token}};

  }
}