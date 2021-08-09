<template>
</template>

<script>
import petService from "@/service/PetService";

export default{
name: "search-pet",
data(){
    return{
        filters: {
            species: "",
            zipcode: "",
        }
    };
},
methods: {

},
computed:{
    filteredResults(){
      
    }
}
}
</script>

<style>
</style>