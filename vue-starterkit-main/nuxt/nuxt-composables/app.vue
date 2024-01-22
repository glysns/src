<template>
  <div>
    <label>Palavra</label>
    <input v-model="palavra"/>
    {{ upper(palavra) }}
    {{ lower(palavra) }}
    <button @click="show()">Show</button>
  </div>
</template>

<script setup lang="ts">
  const {upper, lower} = useFormatacao();
  const palavra = ref('AbCd')
  
  const show = () =>{
      alert(upper(palavra.value) + " " + lower (palavra.value))
  }

</script>