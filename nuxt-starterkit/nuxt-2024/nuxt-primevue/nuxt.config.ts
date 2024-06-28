// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  devtools: { enabled: true },
  modules: [
    '@primevue/nuxt-module'
],
primevue: {
  options: { ripple: true },
  components: {
      exclude: ['Editor']
  }
},
})
