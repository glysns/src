<template>
    <div>
        <h1>Exemplos</h1>
        <button @click="dizerBemVindos()">Bem-Vindos</button>
        <p></p>
        <h3>No CAIXA</h3>
        <label>Nome</label>
        <input v-model="espectador" />
        <button @click="dizerBemVindo()">Bem-Vindo</button>
        {{ espectador }}
        <p />
        <h3>Na FILA</h3>
        <ul>
            <li v-for="e in espectadores" @click="conferirIngresso(e)">{{ e.nome }}</li>
        </ul>
        <h3>FUNCOES</h3>
        <h4 @click="a()">Função A</h4>
        <h4 @click="b()">Função B</h4>
        <h4 @click="c()">Função C</h4>
        <h4 @click="d('Gleyson')">Função D</h4>
        <h4 @click="conferirIdade(16)">Função eAdulto com idade 16</h4>
        <h4 @click="conferirIdade(18)">Função eAdulto com idade 18</h4>
        <h4 @click="logicaComplexa()">Função com lógica em +1 linha</h4>
        <h4 @click="gerarId()">Método utilizando uma função utilitária</h4>
        <label>CEP</label>
        <input v-model="cepId" />
        <button @click="chamarApiViaCep()">Consulta CEP</button>
        <p />{{ cep }}
        <p/>
        <button @click="listarEventos()">Listando os Eventos com AXIOS</button>
        <p/>

        WAITING ->: {{ waiting }}
        <h3 v-if="waiting">Processando ... </h3>
        <p/>
        {{ eventos }}
    </div>
</template>

<script setup>
import { ref } from 'vue'
const { digaBemVindos, digaBemVindo } = useBoasVindas();
const { uuid} = useIds();
const { getCep} = useViaCepApi();
const { listEventos, waiting} = usePublicApi();

//No Caixa
const espectador = ref('')

//Na Fila
const espectadores = ref([{ nome: 'Gleyson', assento: '11A' }, { nome: 'Izabelly', assento: '11B' }])

const cepId = ref('64000-040')
const cep = ref({})

const dizerBemVindos = () => {
    digaBemVindos();
}

const dizerBemVindo = () => {
    digaBemVindo(espectador.value);
}

const conferirIngresso = (espectador) => {
    alert(`Seja Bem-vindo ${espectador.nome}, seu assento é ${espectador.assento}`)
}

const conferirIdade = (idade) => {
    alert('Idade Adulta? ' + eAdulto(idade))
}

const gerarId = () => {
    alert('UUID = ' + uuid())
}

const chamarApiViaCep = async () => {
    const {data} = await getCep(cepId.value);
    console.log('data->cep', data)
    
    //const data = getCep(cepId.value);
    //console.log('cep->', data)
    cep.value = data
}

const eventos = ref ([])
const listarEventos = async () => {
    //const {data} = await listEventos();
    console.log('vamos começar a requisição')
    const {success, status, body } = await listEventos();
    console.log('a requisição foi executada')
    eventos.value = [];
    if(!success)
        alert('erro')
    else
        eventos.value = body
}

//js arrow function
//https://www.youtube.com/watch?v=S5Mn0qQzJ-0
const a = function() {
  alert("Hello World A!");
}

const b = () => {
  //return "Hello World!"; implicito
  alert("Hello World B!");
}

const c = () => alert("Hello World C!");;

const d = (p) => alert("Hello " + p);

const e = p => alert("Hello " + p);

//const eAdulto = (idade) => idade >= 18;
const eAdulto = idade => idade >= 18; //em caso de 1 parametro, parentese pode ser omitido

const nRandom = () => Math.random(); //em caso de 1 parametro, parentese pode ser omitido

const logicaComplexa = () => {
    alert('alerta 01')
    alert('alerta 02')

    //return
}; 

//https://www.youtube.com/watch?v=S5Mn0qQzJ-0
//estudar arraw function this + com classe

</script>

<style>
li:hover {
    cursor: pointer;
}
h4:hover {
    cursor: pointer;
}
</style>
  