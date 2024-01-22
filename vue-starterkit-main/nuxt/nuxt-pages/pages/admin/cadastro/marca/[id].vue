<template>
    <div>
        Você está visualizando a marca {{ $route.params.id }}

        Você está visualizando a marca pelo ID no Params {{ id }}
    </div>
</template>

<script setup>
    const route = useRoute();
    const id = computed(() => route.params.id);
</script>
