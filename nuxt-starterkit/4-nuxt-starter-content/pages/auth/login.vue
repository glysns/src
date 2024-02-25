<template>
    <div>
        <h1>Pagina Vue</h1>
        <button @click="chamarApiViaCep()">Buscar</button>
        {{ cep }}
    </div>
</template>
<script setup>
import { ref } from 'vue';
const cep = ref({})

const chamarApiViaCep = async () => {
    const { data } = await await useFetch(`https://viacep.com.br/ws/64008230/json/`);
    console.log('data->cep', data)

    //const data = getCep(cepId.value);
    //console.log('cep->', data)
    cep.value = data
}

</script>