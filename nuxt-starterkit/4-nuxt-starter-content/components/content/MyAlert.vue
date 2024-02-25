<template>
    <div class="alert">
      <slot/>
    </div>
</template>

<style scoped>
    .alert{
        border: 1px orange solid ;
        padding:  5px;
        color: red;
    }
</style>