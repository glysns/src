<template>
    <div>
        <h1>/pages/eventos/index.vue</h1>
        <ul>
            <li v-for="i in eventos">
                <NuxtLink :to="`/eventos/${i.tag}`" @click="selecionar(i)">
                    {{ i.id }} - {{ i.titulo }}
                </NuxtLink>
            </li>
        </ul>

        <!-- 
            <nuxt-link :to="{ path: 'test', query: {a: 1, b: 2}}">Test Page</nuxt-link>

        -->
    </div>
</template>
<script setup>

const {data : eventos} = await useFetch("https://iza-saas-api-production.up.railway.app/public/eventos")

const { selecionarEvento } = useReservaService();
const selecionar = (evento) => {
    selecionarEvento(evento);
}

</script>
  