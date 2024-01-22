<template>
    <div>
      <NuxtLink to="/enums" style="color: red;"> enums api local server</NuxtLink>

      <div v-for="i in products">
        <NuxtLink :to="`/products/${i.id}`" style="color:blue"> {{ i.id }} -- {{ i.title }}</NuxtLink>
        {{ i.description }}
  
      </div>
  
      <br> <br> <br> 
      {{ cep }}
    </div>
  </template>
  
  <script setup>
    const {data : products} = await useFetch('https://fakestoreapi.com/products')
  
    const {data : cep} = await useFetch('https://viacep.com.br/ws/01001000/json')

    //const res = await useFetch('https://viacep.com.br/ws/0100100/json')
    //console.log('resposta via cep', res)
  
  </script>