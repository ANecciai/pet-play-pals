import axios from 'axios';

export default {

createPet(pet){
  
    return axios.post(`/pets/`, pet)
  },
  
  editPet(pet, petId){
    return axios.put(`/pets/` + petId, pet)
  },

  getPet(){
    return axios.get(`/pets/username`)
  },

  getAuthConfig(){
    return {headers: {"Authorization": "JWT " + this.$store.state.token}};

  },

  getAllPets(){
    return axios.get(`/allpets`)
  },

  getPetById(id){
    return axios.get(`/pets/${{id}}`)
  },

  getPetByZip(zipcode){
    return axios.get(`/pets/${{zipcode}}`)
  }
}