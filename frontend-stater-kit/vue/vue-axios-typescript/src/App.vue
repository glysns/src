<template>
  <div>
    <button @click='listar()'>Listar Clientes</button>
    <button @click='buscar()'>Buscar Cliente -> ID</button>
    <input type='text' placeholder='ID' size='1' v-model='id'/>
    <button @click='alterar()'>Alterar ID 1</button>
    <button @click='incluir()'>Incluir Novo</button>
    <button @click='excluir()'>Excluir</button>
    <input type='text' placeholder='ID' size='1' v-model='id'/>
    <h4>Resposta</h4>
    {{  resposta }}
  </div>

</template>
<script lang="ts">
import { defineComponent } from 'vue'

import { clienteResource } from '@/http/cliente-resource';


export default defineComponent({
  data(){
    return {
      id:0,
      resposta:'SEM RESPOSTA'
    } 
  },
  methods:{
    async listar(){
      console.log('listando ... ')
      const response:any = await clienteResource.listar();
      this.resposta = response.data;
      console.log('listagem concluida ')
    },
    async buscar(){
      console.log('buscando ... ')
      const response = await clienteResource.buscar(this.id);
      this.resposta = response.data;
      console.log('busca concluida ... ')
    },
    async alterar(){
      const registro= { 'nome': 'gleyson sampaio de oliveira', 'cpf': '897870', 'dataNascimento': '1982-01-01', 'rendaMensal': 1234.5, 'sexo': 'MASCULINO', 'id': 1 }
      console.log('alterando ... ')
      const response = await clienteResource.alterar(1,registro);
      this.resposta = response.data;
      console.log('alteração concluida ... ')
    },
    async incluir(){
      const registro= { 'nome': 'marilene sampaio', 'cpf': '4564567', 'dataNascimento': '1967-04-01', 'rendaMensal': 9097.5, 'sexo': 'FEMININO'}
      console.log('incluindo ... ')
      const response = await clienteResource.incluir(registro);
      this.resposta = response.data;
      console.log('inclusão concluida ... ')
    },
    async excluir(){
      console.log('excluindo ... ')
      const response = await clienteResource.excluir(this.id);
      this.resposta = response.data;
      console.log('exclusão concluida ... ')
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
