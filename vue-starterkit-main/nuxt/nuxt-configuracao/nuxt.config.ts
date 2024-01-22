// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  devtools: { enabled: true },
  alias:{
    
  },
  app:{
    //baseURL:process.env.BASE_URL || "http://localhost:3000"
  },
  css: [
    // Load a Node.js module directly (here it's a Sass file).
    //'bulma',
    // CSS file in the project
    '@/assets/css/main.css',
    // SCSS file in the project
    // '@/assets/css/main.scss'
  ]
})
