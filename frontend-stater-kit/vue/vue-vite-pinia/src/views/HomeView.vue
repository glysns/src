<script setup>
import { storeToRefs } from 'pinia';
import { ref, onMounted, computed } from 'vue'
import { aulasStore } from '../stores/aulas'
import {publicResource} from '@/_webservices/public-service'
const store = aulasStore();

//const aulas = store.aulas;

//destruction
const {aulas} = storeToRefs(store);

//só para o component atual - se precisar globalizar
//use o getters da store
const size = computed(()=>store.size)

const exists = store.exists(10);
onMounted(() => {
  
})

function assistirAula( id){
  store.assistirAula(id)
}

async function concluirAula( id){
  console.log('processando conclusão aula')
  await store.concluirAula(id)
  console.log('conclusão aula realizada com sucesso')
}

async function logarOk(){
  console.log('iniciando login - OK')
  //OK
  //const response = await publicResource.logar({'email':'convidado@digytal.com.br', 'password':'welcome'});
  //console.log(response);
  const {status, data} = await publicResource.logar({'email':'convidado@digytal.com.br', 'password':'welcome'});
  console.log('status -> ', status)
  console.log('data -> ', data)

  console.log('finalizando login - OK')
}
async function logarNo(){
  console.log('iniciando login - NO')
  //const response = await publicResource.logar({'email':'convidado@digytal.com.br', 'password':'welcomes'});
  //console.log(response);
  const {status, data} = await publicResource.logar({'email':'convidado@digytal.com.br', 'password':'welcomes'});
  
  console.log('status -> ', status)
  console.log('data -> ', data)

  console.log('finalizando login - NO')
}

</script>

<template>
  <div>
    <h1>This is HOME page</h1>
    <p>total de aulas: {{ size }}</p> 
    
    <p></p>
    {{ exists }}

    <template v-for="(a) in aulas" :key="a.id">
      <p></p> 
      <button @click="assistirAula(a.id)">Assisitir {{ a.id }} - {{ a.titulo }}</button>
      <p></p> 
      <button @click="concluirAula(a.id)">Concluir {{ a.id }} - {{ a.titulo }}</button> 
    </template>
    <p></p> 
    {{ aulas }}
    <p></p> 
    <button @click="logarOk()">Logar - OK</button> 
    <button @click="logarNo()">Logar - NO</button> 
  </div>
</template>

//0800 400 4758