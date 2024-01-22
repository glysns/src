<template>
  <div>
    Pedido
    {{ pedido.total }}
    <button @click="add()">Add</button>
    <button @click="remove()">Remove</button>
    <button @click="zera()">Zera</button>
  </div>
</template>

<script setup lang="ts">
import { usePedidoState } from './composables/state/usePedidoState';

  const pedido =usePedidoState();
  const add = () =>{
      pedido.value.total = pedido.value.total + 5; 
  }
  const remove = () =>{
      pedido.value.total = pedido.value.total - 1; 
  }
  const zera = () =>{
      pedido.value.total = 0; 
  }
</script>
