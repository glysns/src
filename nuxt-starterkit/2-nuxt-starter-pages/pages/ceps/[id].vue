<template>
    <div>
        <h2><NuxtLink to="/ceps"> Voltar </NuxtLink></h2>
        <h1>/pages/ceps/index.vue</h1>
        <h2>CEP Escolhido: {{ $route.params.id }}</h2>
        <h2>Variavel cepEscolhido</h2>
        <p>
            {{ cepEscolhido }}
        </p>

        <h2>Variavel mapeada - cep</h2>
        <p>
            {{ cep }}
        </p>

        <h2>Evento obtido pelo useReservaService - cep</h2>
        <p>
            {{ evento }}
        </p>
    </div>
</template>

<script setup>

import {ref} from 'vue';

const {evento} = useReservaService();

const cepsApi = [{ id: 64000040, logradouro: 'Rua das flores' }, { id: 64000128, logradouro: 'Avenida 7 de Setembro' }]

const { id } = useRoute().params //promisse + desestruturação (destruction)

const cepEscolhido = ref({});

const {data : cep} = await useFetch(`https://viacep.com.br/ws/${id}/json/`)

cepEscolhido.value = cep;
console.log('data foi', cep);
 
/*
(async () => {
  try {
    const response = await $fetch(`https://viacep.com.br/ws/${id}/json/`);
    cepEscolhido.value = response.data; // Assuming your data is in response.data
  } catch (error) {
    console.error(error);
  }
})();

onMounted( async () => {
    //console.log(cepsApi)
    console.log('CEP Escolhido para eu fazer qualquer coisa na minha lógica', id)
    const { data } = await useFetch(`https://viacep.com.br/ws/${id}/json/`)
    
    console.log('data foi', data);

    cepEscolhido.value = data;

    // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/find
    
    //filter
    
    cepEscolhido.value = cepsApi.find(function (el) {
        return el.id == id
    });

    console.log(cepEscolhido)
    
})
*/
</script>
  