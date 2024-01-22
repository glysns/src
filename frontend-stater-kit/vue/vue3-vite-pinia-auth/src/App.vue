<template>
  <ul>
    <li>
      <router-link :to="{name:'home'}">
        Home
      </router-link>
    </li>
    <li>
      <router-link :to="{name:'login'}">
        Login
      </router-link>
    </li>
    <li>
      <router-link :to="{name:'dashboard'}">
        Dashboard
      </router-link>
    </li>
    <li>
      <template v-if="auth.isAuthenticated">
        Olá {{ auth.fullName }} 
        <button @click="logout">
          Logout
        </button>
      </template>
      <template v-else>
        Olá visitante
      </template>
    </li>
  </ul>

  <router-view />
</template>

<script setup>
import {useAuth} from '@/stores/auth.js';
import {useRouter} from 'vue-router';

const auth = useAuth();
const router = useRouter();

function logout(){
  auth.clear();
  router.push({name:'login'});
}
</script>

<style lang="scss" scoped>

</style>