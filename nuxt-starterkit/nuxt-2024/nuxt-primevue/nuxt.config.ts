// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  devtools: { enabled: true },
  modules: [
    '@primevue/nuxt-module'
],
primevue: {
  components: {
      include: ['Button', 'DataTable']
  }
}
})
