<template>
    <div>
        <h1>/pages/ceps/index.vue</h1>

      

        <div>{{ ceps }}</div>

        <ul>
            <li v-for="cep in ceps"><a :href="`/ceps/${cep}`">{{ cep }}</a> </li>
        </ul>

        <ul>
            <li v-for="i in cepsObjects">
                <NuxtLink :to="`/ceps/${i.id}`" @click="selecionar(i)">
                    {{ i.cep }} - {{ i.logradouro }}
                </NuxtLink>
            </li>
        </ul>

        <!-- 
            <nuxt-link :to="{ path: 'test', query: {a: 1, b: 2}}">Test Page</nuxt-link>

        -->
    </div>
</template>
<script setup>
const ceps = [64000040, 64000128]
const cepsObjects = [

    {
        "id": 64000040,
        "cep": "64000-040",
        "logradouro": "Praça da Liberdade",
        "complemento": "",
        "bairro": "Centro",
        "localidade": "Teresina",
        "uf": "PI",
        "ibge": "2211001",
        "gia": "",
        "ddd": "86",
        "siafi": "1219"
    },
    {
        "id": 64000128,
        "cep": "64000-128",
        "logradouro": "Rua Magalhães Filho",
        "complemento": "(Zona Norte) - de 1/2 a 799/800",
        "bairro": "Centro",
        "localidade": "Teresina",
        "uf": "PI",
        "ibge": "2211001",
        "gia": "",
        "ddd": "86",
        "siafi": "1219"
    }

]

//const {data : eventos} = await useFetch("https://iza-saas-api-production.up.railway.app/public/eventos")

const { selecionarEvento } = useReservaService();
const selecionar = (evento) => {
    selecionarEvento(evento);
}

</script>
  