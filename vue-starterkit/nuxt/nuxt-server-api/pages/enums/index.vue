<template>
    <div>
        Consumindo Listas Estaticas ou Enums
        <br><br>
       
        <select v-model="uf" >
    <option disabled value="">Selecione um estado</option>
    <option
      v-for="i in ufs"
      :key="i.sigla"
      :value="i"
    >
      {{ i.nome }}
    </option>
  </select>

  <br><br>

  estado selecionado é : {{ uf }}

    </div>
</template>

<script setup>
    const {data:ufs} = await useFetch('/api/ufs');
    const uf = ref()



    //https://vuejs.org/guide/essentials/forms.html#select
</script>

<style lang="scss" scoped>

</style>