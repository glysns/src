<template>
  <div>
    <button @click='listar()'>Listar Clientes</button>
    <button @click='buscar()'>Buscar Cliente -> ID</button>
    <input type='text' placeholder='ID' size='1' v-model='id'/>
    <button @click='alterar()'>Alterar ID 1</button>
    <button @click='incluir()'>Incluir Novo</button>
    <button @click='excluir()'>Excluir</button>
    <input type='text' placeholder='ID' size='1' v-model='id'/>
    <h4>Resposta Ref</h4>
    {{  resposta }}
    <h4>Resposta Reativa</h4>
    {{  body }}

    <h4 @click="showBody">Show</h4>
    {{  bodys }}
  </div>

</template>
<script lang="ts">
import { defineComponent, ref, reactive } from 'vue'

import { clienteResource } from '@/http/cliente-resource';

export default defineComponent({
  setup() {
    let id = ref(0);
    let resposta = ref('SEM RESPOSTA');
    let body = ref({});
    let bodys = ref({});

    async function listar(){
      console.log('listando ... ')
      const response:any = await clienteResource.listar();
      resposta.value = response.data;
      body.value = response.data;
      console.log(body)
      console.log('listagem concluida ')
    }

    async function buscar(){
      console.log('buscando ... ')
      const response = await clienteResource.buscar(id.value);
      resposta.value = response.data;
      console.log('busca concluida ... ')
    }

    async function alterar(){
      const registro= { 'nome': 'gleyson sampaio de oliveira', 'cpf': '897870', 'dataNascimento': '1982-01-01', 'rendaMensal': 1234.5, 'sexo': 'MASCULINO', 'id': 1 }
      console.log('alterando ... ')
      const response = await clienteResource.alterar(1,registro);
      resposta.value = response.data;
      console.log('alteração concluida ... ')
    }

    async function incluir(){
      const registro= { 'nome': 'marilene sampaio', 'cpf': '4564567', 'dataNascimento': '1967-04-01', 'rendaMensal': 9097.5, 'sexo': 'FEMININO'}
      console.log('incluindo ... ')
      const response = await clienteResource.incluir(registro);
      resposta.value = response.data;
      console.log('inclusão concluida ... ')
    }

    async function excluir(){
      console.log('excluindo ... ')
      const response = await clienteResource.excluir(id.value);
      resposta.value = response.data;
      console.log('exclusão concluida ... ')
    }

    const showBody = () => {
      alert('SHOW')
        bodys.value = body
    }

    return{
      id, resposta, body, bodys,
      listar,buscar, incluir, alterar, excluir, showBody
    }
  }

  
})
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
