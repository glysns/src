<template>
  <div>
    <h1>Vue + Vite + Reactivity</h1>
    <label >Integrante
      <input type="text" v-model="integrante.nome" />
    </label>
    <br>
    <label >Profissão
      <input type="text" v-model="integrante.profissao" />
    </label>
    <button v-on:click="adicionar()" type="button"> Adicionar</button>
    <ul>
      <li v-for="membro in time"> {{ membro.nome }} - {{ membro.profissao }}</li>
    </ul>
  </div>
</template>

<script setup>
  import {ref, reactive} from 'vue';

  //ref -> utilizado para tipos literais ou primitivos
  //const integrante = ref('') 

  //reactive -> utilizado para objetos
  const integrante = reactive({nome:'', profissao:''}) 
  
  const time = ref([])


  const adicionar = () => {
    //ref
    //time.value.push(integrante.value)

    time.value.push(integrante)
  }

</script>

