<template>
  <div>
    <button @click='logar()'>Login</button>
    <button @click='logarErro()'>Login - Erro</button>
    <button @click='listar()'>Listar Clientes</button>
    <button @click='buscar()'>Buscar Cliente -> ID</button>
    <input type='text' placeholder='ID' size='1' v-model='id'/>
    <button @click='alterar()'>Alterar ID 1</button>
    <button @click='incluir()'>Incluir Novo</button>
    <button @click='excluir()'>Excluir</button>
    <input type='text' placeholder='ID' size='1' v-model='id'/>
    
    <h4>Resposta</h4>
    <textarea v-model="resposta" rows="15" cols="100" :style="{ 'background': color }"></textarea>
    <h4>Resposta Data (Body)</h4>
    <textarea v-model="body" rows="15" cols="100" :style="{ 'background': color }"></textarea>

    <h4>Login Input</h4>
    <input placeholder="usuario" v-model="login.usuario">
    <input placeholder="senha" v-model="login.senha">
    <button @click='logarInput()'>Login Input</button>
    <p>{{login}}</p>

  </div>

</template>
<script lang="ts">
import { defineComponent, ref, reactive } from 'vue'

import { clienteResource } from '@/http/cliente-resource';
import { publicResource } from '@/http/public-resource';
import { AxiosError } from 'axios';

export default defineComponent({
  setup() {
    let login = ref({'usuario':'admin@digytal.com.br','senha':'123'})
    let id = ref(0);
    let resposta = ref('SEM RESPOSTA');
    let body = ref('SEM CORPO');
    let color = ref('white');
    async function listar(){
      console.log('listando ... ')
      try {
        const response:any = await clienteResource.listar();
        resposta.value = JSON.stringify(response,null, 2);
        body.value = JSON.stringify(response.data,null, 2);
        color.value='greenyellow'
      }

      catch (e) {
          const err:any = (e as AxiosError).response;
          alert(err?.data.message);

          resposta.value = JSON.stringify(err,null, 2);
          body.value = JSON.stringify(err?.data,null, 2);
          color.value='red'
      }
      
      console.log('listagem concluida ')
    }

    async function buscar(){
      console.log('buscando ... ')
      const response = await clienteResource.buscar(id.value);
      resposta.value = JSON.stringify(response,null, 2);
      body.value = JSON.stringify(response.data,null, 2);
      console.log('busca concluida ... ')
    }
    function reset(){
      resposta.value = 'AGUARDANDO ...';
      body.value = 'AGUARDANDO ...';
    }
    async function alterar(){
      reset()
      const registro= { 'nome': 'gleyson sampaio de oliveira', 'cpf': '897870', 'dataNascimento': '1982-01-01', 'rendaMensal': 1234.5, 'sexo': 'MASCULINO', 'id': 1 }
      console.log('alterando ... ')
      const response = await clienteResource.alterar(1,registro);
      resposta.value = JSON.stringify(response,null, 2);
      body.value = JSON.stringify(response.data,null, 2);
      console.log('alteração concluida ... ')
    }

    async function incluir(){
      reset()
      
      const registro= { 'nome': 'marilene sampaio', 'cpf': '4564567', 'dataNascimento': '1967-04-01', 'rendaMensal': 9097.5, 'sexo': 'FEMININO'}
      console.log('incluindo ... ')
      const response = await clienteResource.incluir(registro);
      resposta.value = JSON.stringify(response,null, 2);
      body.value = JSON.stringify(response.data,null, 2);
      console.log('inclusão concluida ... ')
    }

    async function excluir(){
      reset()
      
      console.log('excluindo ... ')
  
      try {
        const response:any = await clienteResource.excluir(id.value);
        resposta.value = JSON.stringify(response,null, 2);
        body.value = JSON.stringify(response.data,null, 2);
        color.value='#0bfc04'
      }

      catch (e) {
          const err:any = (e as AxiosError).response;
          alert(err?.data.message);

          resposta.value = JSON.stringify(err,null, 2);
          body.value = JSON.stringify(err?.data,null, 2);
          color.value='#fc2947'
      }
      
      console.log('exclusao concluida ')
    }

    async function logar(){
      reset()
      
      localStorage.removeItem('token');
      console.log('logando ... ')
      const registro= {'username': 'digytal','password': 'Jwt@123'}
      const response = await publicResource.logar(registro);
      resposta.value = JSON.stringify(response,null, 2);
      body.value = JSON.stringify(response.data,null, 2);
      console.log('login realizado com sucesso ')


      //atualizando o localStorage com token e usuario
      localStorage.setItem('token',response.data.body.token);
    }
    
    async function logarErro(){
      reset()
      
      localStorage.removeItem('token');
      console.log('logando ... ')
      const registro= {'username': 'desconhecido','password': 'errada'}
      const response = await publicResource.logar(registro);
      resposta.value = JSON.stringify(response,null, 2);
      body.value = JSON.stringify(response.data,null, 2);
      console.log('login realizado com sucesso ')
    }

    async function logarInput(){
      reset()
      
      localStorage.removeItem('token');
      try {
        const response:any = await publicResource.logar(login.value);

        resposta.value = JSON.stringify(response,null, 2);
        body.value = JSON.stringify(response.data,null, 2);
        color.value='#0bfc04'
        localStorage.setItem('token',response.data.body.token);
      }

      catch (e) {
          const err:any = (e as AxiosError).response;
          console.log(err)

          resposta.value = JSON.stringify(err,null, 2);
          body.value = JSON.stringify(err?.data,null, 2);
          color.value='#fc2947'
      }
    }
    return{
      id, resposta, body, color,login,
      listar,buscar, incluir, alterar, excluir, logar, logarErro,logarInput
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
