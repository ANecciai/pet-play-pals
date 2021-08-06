<template>
<div>
  <login/>
  <register/>
</div>
  
</template>

<script>
import Login from '@/views/Login';
import Register from '@/views/Register';

export default {
    name: "login-register",
    components:{
        Login,
        Register
    }

}
</script>

<style>

</style>