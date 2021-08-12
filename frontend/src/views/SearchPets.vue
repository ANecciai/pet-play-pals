<template>

<div class="search">
    
<div class="search-params">
<label>Species</label>
</div>
<div>
    <select v-model="filters.species">
    <option></option>
    <option value="dog">Dog</option>
    <option value="cat">Cat</option>
    <option value="bird">Bird</option>
    </select>
    </div>
    <div>
<label>Zip Code</label> 
    </div>
    <div>
    <input type="text" placeholder="Zip Code" v-model="filters.zipcode" />
    </div>
    <div>
<button class="searchPets" value="SEARCH" v-on:click.prevent="filterResults">SEARCH FOR PETS</button>
</div>

<div class="results">    
<h3> Search Results </h3>    
<p v-for="pet in filteredPets" v-bind:key="pet.petId">
<router-link v-bind:to="{name: 'pet-details', params: {petId:pet.petId}}">{{pet.name}} </router-link>

</p>
</div>


</div>
</template>

<script>
import petService from "@/services/PetService";

export default{
name: "search-pet",
data(){
    return{
        filters: {
            species: '',
            zipcode: '',
        },
    
        pets:{},
        filteredPets:{},
        }
    },
methods: {
    filterResults(){   
        if (this.filters.zipcode != ''){
            petService.getPetByZip(this.filters.zipcode).then(petsByZip =>{
            this.filteredPets = petsByZip.data.filter(pet => {
                if (pet.species == this.filters.species || this.filters.species == ""){
                    return true;
                }
            })
        }) 
    }else
     this.filteredPets = this.pets.filter(pet => {
         if (pet.species == this.filters.species){
             return true;
         }
     })
    }
},
created(){
    petService.getAllPets().then(response => {
        this.pets = response.data;})
},
computed:{

  
},
}
</script>

<style>


</style>