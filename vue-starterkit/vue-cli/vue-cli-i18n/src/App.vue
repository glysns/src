<template>
  <h2>Vue + Cli + i18n v9</h2>

  <h2>{{ $t("selectLanguage") }}</h2>
  <select v-model="selected" @change="changeLocale(selected)">
    <option disabled value="">{{ $t("selectLanguage") }}</option>
    <option
      v-for="lang in languages"
      :key="lang.language"
      :value="lang.language"
    >
      {{ lang.title }}
    </option>
  </select>

  <br />
  <br />
  Mensagem: {{ $t("welcome") }}
  <br />
  Idioma: {{ $t("language") }}

  <br />
  <h4>Numero original</h4>
  <p>{{ valor }}</p>
  
  <h4>Numero Padrão</h4>
  <p>{{ $n (valor) }}</p>
  
  <h4>Numero Decimal</h4>
  <p>{{ $n (valor, 'decimal') }}</p>

  <h4>Numero Monetário</h4>
  <p>{{ $n (valor, 'currency') }}</p>
  

  <h4>Data Padrão</h4>
  <p>{{ $d (new Date()) }}</p>
  <h4>Hora</h4>
  <p>{{ $d (new Date(),'time') }}</p>    
  <h4>Data Curta</h4>
  <p>{{ $d (new Date(), 'short') }}</p>
  <h4>Data Média</h4>
  <p>{{ $d (new Date(), 'medium') }}</p>
  <h4>Data Longa</h4>
  <p>{{ $d (new Date(), 'long') }}</p>
  <h4>Data Hora</h4>
  <p>{{ $d (new Date(), 'dateTime') }}</p>
</template>


<script>
import { i18n, languages } from "./locales";
export default {
  name: 'App',
  data() {
    //variaveis da intancia do objeto que representam dados mediante a interação do usuário
    return {
      selected:'pt-BR',
      valor:10025.9157,
      languages:languages
    };
  },
  methods: {
      changeLocale(locale) {
        i18n.global.locale.value = locale;
      }
    },
}
</script>


<style >
:root {
  font-family: Inter, system-ui, Avenir, Helvetica, Arial, sans-serif;
  line-height: 1.5;
  font-weight: 400;

  color-scheme: light dark;
  color: rgba(255, 255, 255, 0.87);
  background-color: #242424;

  font-synthesis: none;
  text-rendering: optimizeLegibility;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  -webkit-text-size-adjust: 100%;
}

a {
  font-weight: 500;
  color: #646cff;
  text-decoration: inherit;
}
a:hover {
  color: #535bf2;
}

a {
  font-weight: 500;
  color: #646cff;
  text-decoration: inherit;
}
a:hover {
  color: #535bf2;
}

body {
  margin: 0;
  display: flex;
  place-items: center;
  min-width: 320px;
  min-height: 100vh;
}

h1 {
  font-size: 3.2em;
  line-height: 1.1;
}

button {
  border-radius: 8px;
  border: 1px solid transparent;
  padding: 0.6em 1.2em;
  font-size: 1em;
  font-weight: 500;
  font-family: inherit;
  background-color: #1a1a1a;
  cursor: pointer;
  transition: border-color 0.25s;
}
button:hover {
  border-color: #646cff;
}
button:focus,
button:focus-visible {
  outline: 4px auto -webkit-focus-ring-color;
}

.card {
  padding: 2em;
}

#app {
  max-width: 1280px;
  margin: 0 auto;
  padding: 2rem;
  text-align: center;
}

@media (prefers-color-scheme: light) {
  :root {
    color: #213547;
    background-color: #ffffff;
  }
  a:hover {
    color: #747bff;
  }
  button {
    background-color: #f9f9f9;
  }
}

</style>