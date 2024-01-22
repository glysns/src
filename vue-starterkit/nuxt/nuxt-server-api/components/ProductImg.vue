<template>
    <div>
       <img :src="product.image"/>

       Descrição no componentes
       {{ product }}
    </div>
</template>

<script setup>
    const { product } = defineProps(['product']) 
</script>
