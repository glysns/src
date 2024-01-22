<template>
    <div>
       Eu sou o conteúdo que será apresentado quando selecionarmos um produto
        ID: {{ id }}
        <br><br>
        Titulo: {{ product.title }}
        <br><br>
        Descrição: {{ product.description }}

        <br>
        Imagem
        <br>
        <ProductImg :product="product"/>

    </div>
</template>

<script setup>

    const { id } = useRoute().params;
    const {data : product} = await useFetch(`https://fakestoreapi.com/products/${id}`, {key:id})
  
</script>
