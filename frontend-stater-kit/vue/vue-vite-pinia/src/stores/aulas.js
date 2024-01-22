import { defineStore } from 'pinia'

import axios from 'axios'

export const aulasStore = defineStore('aulas', {
  state:() =>{
    return {
        aulas:[{"id":1,"titulo":"Java Setup","situacao":{"id":null,"usuario":null,"dataHoraInicio":null,"dataHoraFim":null},"assistida":false},{"id":2,"titulo":"Variaveis","situacao":{"id":null,"usuario":null,"dataHoraInicio":null,"dataHoraFim":null},"assistida":false},{"id":3,"titulo":"Controle de fluxo","situacao":{"id":null,"usuario":null,"dataHoraInicio":null,"dataHoraFim":null},"assistida":false}]
    }
  },
  getters:{
    //size:(state) => {return state.aulas.length }
    size:(state) => state.aulas.length,
    exists:(state) => {
        return (id) => state.aulas.some(a=>a.id==id)
    }
  },
  actions:{
    //atualiza os nossos states
    assistirAula( id ){
        const aula = this.aulas.find( (x) => x.id ==id );
        aula.situacao.dataHoraInicio = new Date();
    },
    async concluirAula( id ){
        //integração -> response
        const {data} = await this.integrar()
        console.log('res-data', data)
        /*
        res.then((response) => {
            console.log('axios res', response)
          })
        .catch((error) => console.log(error))
        */
        const aula = this.aulas.find( (x) => x.id ==id );
        aula.situacao.dataHoraFim = new Date();
        aula.assistida=true
        
        //try / catch
    },

    integrar (){
        return axios.get('http://localhost:8080/public/store/aulas');
    }
  }
})


//https://www.koderhq.com/tutorial/vue/http-axios/