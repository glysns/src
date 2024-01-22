import { createApp, markRaw } from "vue";
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import { useAuth } from "@/stores/auth.js";

import './assets/main.css'

const app = createApp(App)

const pinia = createPinia();
pinia.use(({ store }) => { store.router = markRaw(router) });

app.use(pinia)
app.use(router)

if (localStorage.getItem('token')) {
  (async () => {
    const auth = useAuth();
    try {
      auth.setIsAuth(true);
       await auth.checkToken();
    } catch (error) {
      auth.clear()
    }
  })()
}

app.mount('#app')
