<!-- Adicione "scoped" para limitir o CSS somente para este componente -->
<style scoped>
  h1{color: red;}
</style>

<template>
  <!-- É necessário que existe somente um elemento RAIZ -->
  <div>

    <h1> {{ variavel }}</h1>
    <input type="text" v-model="variavel" />
    <button
        v-on:click="sobrenome()"
        type="button">
        Sobrenome
      </button>
  </div>
  
</template>

<!-- SINTAXE MAIS CLEAN -->
<script setup>

import { ref } from 'vue'

const variavel = ref('gleyson')

const sobrenome = () => {
  //variavel = variavel + ' sampaio';
  variavel.value = variavel.value + ' sampaio';
}

</script>


<!-- export default ou instancia anonima 

<script>
export default {
  name: 'App',
  data() {
    return {
      variavel:'gleyson'
    };
  },
}
</script>

-->

<!-- composition api 
<script>

import { ref } from 'vue'

export default {
  setup() { //chamando a função setup explicitamente

    // const variavel = 'gleyson'

    //agora faz necessário habilitar o recurso de reatividade em nosso componente
    const variavel = ref('gleyson')

    const sobrenome = () => {
      //variavel = variavel + ' sampaio';
      variavel.value = variavel.value + ' sampaio';
    }

    // expondo nossas variáveis e métodos
    return {
      variavel,
      sobrenome
    }
  }
}
</script>

-->



