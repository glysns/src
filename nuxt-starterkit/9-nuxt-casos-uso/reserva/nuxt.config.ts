// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  devtools: { enabled: true },
  imports: {
    dirs: [
      'composables/**',
      'utils/**'
    ]
  },
  runtimeConfig: {
    public: {
      apiBase: '', // can be overridden by NUXT_PUBLIC_API_BASE environment variable
    }
  },
  css: ['~/assets/css/main.min.css'],
  app:{
    head:{
      script:[
        {src:'/js/backend-bundle.min.js'},
        {src:'/js/customizer.js'},
        {src:'/js/app.js'}
      ]
    }
  }
})
