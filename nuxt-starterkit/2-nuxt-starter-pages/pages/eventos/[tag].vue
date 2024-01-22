<template>
    <div>
        <h2><NuxtLink to="/eventos"> Voltar </NuxtLink></h2>
        <h1>/pages/eventos/[ID].vue</h1>
        <h2>Evento Escolhido: {{ $route.params.tag }}</h2>
        <h2>Evento:  {{ evento.id }}</h2>
        <p>
            {{ evento }}
        </p>
    </div>
</template>

<script setup>
const {evento} = useReservaService();
</script>
  