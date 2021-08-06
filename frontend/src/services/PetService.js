import axios from 'axios';

export default {

createPet(pet){
    return axios.post(`/pets/`, pet)
  },
  
  editPet(pet, petId){
    return axios.put(`/pets/` + petId, pet)
  }
}