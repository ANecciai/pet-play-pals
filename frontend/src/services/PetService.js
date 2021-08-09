import axios from 'axios';

export default {

createPet(pet){
  
    return axios.post(`/pets/`, pet)
  },
  
  editPet(pet, petId){
    return axios.put(`/pets/` + petId,  pet)
  },

  getPet(pet){
    return axios.get(`/pets/username`, pet)
  },

  getAuthConfig(){
    return {headers: {"Authorization": "JWT " + this.$store.state.token}};

  }
}