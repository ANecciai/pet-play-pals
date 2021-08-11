import axios from 'axios';

export default {

createPet(pet){
  
    return axios.post(`/pets/`, pet)
  },
  
  editPet(pet, petId){
    return axios.put(`/pets/` + petId, pet)
  },

  getMyPets(){
    return axios.get(`/mypets`)
  },


  getAllPets(){
    return axios.get(`/allpets`)
  },

  getPetById(id){
    return axios.get(`/pets/${id}`)
  },

  getPetByZip(zipcode){
    return axios.get(`/pets/zipcode/${zipcode}`)
  },
  deletePet(id){
    return axios.delete(`/pets/delete`, id)
  },
  getPetsByUsername(username){
    return axios.get(`/pets/username/${username}`)
  }
}