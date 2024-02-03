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
  css: ['~/assets/css/style.css','~/assets/css/plugins.css'],
  app:{
    head:{
      script:[
        {src:'/js/jquery.js'},
        {src:'/js/plugins.js'},
        {src:'/js/init.js'}
        
      ]
    }
  }
})
