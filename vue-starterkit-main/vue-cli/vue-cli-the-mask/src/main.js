import { createApp } from 'vue'
import App from './App.vue'

//lib para disponibilizar as diretivas e recursos de mascara
import {mask} from 'vue-the-mask'

//lib para disponibilizar as diretivas e recursos de mascara para moeda
import { Money3Directive } from 'v-money3'

const app = createApp(App)

// apelidando a diretiva para nosso projeto
app.directive('mask',mask)

// apelidando a diretiva para nosso projeto
app.directive('money',Money3Directive)

app.mount('#app')
