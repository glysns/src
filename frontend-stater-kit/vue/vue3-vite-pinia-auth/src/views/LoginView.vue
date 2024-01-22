<template>
  <h2>Login</h2>

  <template v-if="!auth.isAuthenticated">
    <form @submit.prevent="login">
      <input
        v-model="user.usuario"
        type="text"
        placeholder="Seu email"
      >
      <input
        v-model="user.senha"
        type="password"
        placeholder="Sua senha"
      >
      <button type="submit">
        Login
      </button>
    </form>
  </template>

  <template v-else>
    Logen id
  </template>
</template>

<script setup>
//import http from '@/services/http.js';
import publicService from '@/services/public-service.js';

import {reactive} from 'vue';
import {useAuth} from '@/stores/auth.js';

const auth = useAuth();

const user = reactive({
  email:'jordane09@example.net',
  password:'123'
})

async function login(){
  try {
    console.log(user)
    const data = await publicService.logar(user);
    console.log(data);
    //auth.setToken(data.token);
    //auth.setUser(data.user);
  } catch (error) {
    console.log(error?.response?.data);
  }
}

</script>
