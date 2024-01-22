import { createI18n } from 'vue-i18n'
import pt_BR from './messages/pt-BR.json'
import en_US from './messages/en-US.json'

const messages = {
    "pt-BR": pt_BR,
    "en-US": en_US
}

const datetimeFormats = {
    'en-US': {
        short: {
            year: 'numeric', month: 'numeric', day: 'numeric'
        },
        medium: {
            year: 'numeric', month: 'numeric', day: 'numeric',
            weekday: 'short', hour: 'numeric', minute: 'numeric'
        },
        long: {
            year: 'numeric', month: 'short', day: 'numeric',
            weekday: 'short', hour: 'numeric', minute: 'numeric'
        },
        time: {
            hour: 'numeric', minute: 'numeric'
        },
        dateTime: {
            year: 'numeric', month: 'numeric', day: 'numeric',
            hour: 'numeric', minute: 'numeric'
        },
    },
    'pt-BR': {
        short: {
            year: 'numeric', month: 'numeric', day: 'numeric'
        },
        medium: {
            year: 'numeric', month: 'numeric', day: 'numeric',
            weekday: 'short', hour: 'numeric', minute: 'numeric'
        },
        
        long: {
            year: 'numeric', month: 'short', day: 'numeric',
            weekday: 'short', hour: 'numeric', minute: 'numeric'
        },
        time: {
            hour: 'numeric', minute: 'numeric'
        },
        dateTime: {
            year: 'numeric', month: 'numeric', day: 'numeric',
            hour: 'numeric', minute: 'numeric'
        },
    }
}

const numberFormats = {
    'pt-BR': {
      currency: {
        style: 'currency', currency: 'BRL', notation: 'standard'
      },
      decimal: {
        style: 'decimal', minimumFractionDigits: 2, maximumFractionDigits: 2
      }
    },
    'en-US': {
        currency: {
          style: 'currency', currency: 'USD', notation: 'standard'
        },
        decimal: {
          style: 'decimal', minimumFractionDigits: 2, maximumFractionDigits: 2
        }
      }
    
  }

export const i18n = createI18n({
    legacy: false,
    locale: 'pt-BR',
    fallbackLocale: 'pt-BR',
    messages: messages,
    datetimeFormats,
    numberFormats
})

//export default i18n - SE NÃO FOR MUDAR O IDIOMA

export const languages = [
    { language: 'pt-BR', title: 'Portugês BR' },
    { language: 'en-US', title: 'English' },
]
