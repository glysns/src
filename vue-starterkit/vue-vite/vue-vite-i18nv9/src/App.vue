<script setup>
import { ref } from "vue";
import { i18n, languages } from "./locales";
const selected = ref("");
const valor = 10025.9157
const changeLocale = (locale) => {
  i18n.global.locale.value = locale;
};
</script>

<template>
  <h2>Vue + Vite + i18n v9</h2>

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
