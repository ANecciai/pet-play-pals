<template>

<div class="search">
    
<div class="search-params">
<label>Species: <select v-model="filters.species">
    <option value="dog">Dog</option>
    <option value="cat">Cat</option>
    <option value="bird">Bird</option>
    </select>
</label>
<label>Zip Code: <input type="text" placeholder="Zip Code" v-model="filters.zipcode" /> </label>

</div>

<div class="results">    
<h3> Search Results </h3>    
<p v-for="pet in pets" v-bind:key="pet.id">
<router-link v-bind:to="{name: 'pet-details', params: {petId:pet.id}}">{{pet.name}} </router-link>

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
            species: "",
            zipcode: "",
        },
    
        pets:{},
        filteredPets:{},
        }
    },
methods: {
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