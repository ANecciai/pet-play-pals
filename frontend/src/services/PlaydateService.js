import axios from 'axios';

export default {

createPlaydate(playdate, username){
    return axios.post(`/playdates/create/${username}`, playdate)
  },
  
  editPlaydate(playdate, playdateId){
    return axios.put(`/playdates/edit` + playdateId, playdate)
  },

getMyPlaydates(){
  return axios.get(`/myplaydates`)
},  

getPlaydateById(playdateId){
  return axios.get(`/playdates/${playdateId}`)
},

acceptPlaydate(playdateId){
  return axios.put(`/playdates/accept/${playdateId}`)
},
declinePlaydate(playdateId){
  return axios.put(`/playdates/decline/${playdateId}`)
},
cancelPlaydate(playdateId){
  return axios.put(`/playdates/cancel/${playdateId}`)
}

  
}