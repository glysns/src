<template>
  <div>
    <br>
    <h1>Auxiliar</h1>
    <button @click="preencher()">Preencher</button>
    <button @click="limpar()">Limpar</button>
    <br>
    <label>
      CPF:
      <input type="text" v-model="cpf" v-mask="'###.###.###-##'"  />
      CPF MASK:{{ cpf }}
      CPF RAW: {{ unmask(cpf) }}
    </label>
    <br>
    <label>
      CNPJ:
      <input type="text" v-model="cnpj" v-mask="'##.###.###/####-##'"  />
      CNPJ MASK:{{ cnpj }}
    </label>

    <br>
    <label>
      CPF/CNPJ:
      <input type="text" v-model="cpfCnpj" v-mask="['###.###.###-##', '##.###.###/####-##']"  />
      CPF/CNPJ MASK:{{ cpfCnpj }}
    </label>

    <br>
    <label>
      TELEFONE:
      <input type="text" v-model="telefone" v-mask="'(##) ####-####'"  />
      TELEFONE:{{ telefone }}
    </label>

    <br>
    <label>
      CELULAR:
      <input type="text" v-model="celular" v-mask="'(##) #####-####'"  />
      CELULAR:{{ celular }}
    </label>

    <br>
    <label>
      DATA:
      <input type="text" v-model="data" v-mask="'##/##/##'"  />
      DATA:{{ data }}
    </label>

    <br>
    <label>
      MOEDA:
      <input v-model.lazy="moeda" v-money="moneyMask" />
      MOEDA:{{ data }}
    </label>

    <br>
    <h1>Exibindo os campos sem mascara</h1>
    <button @click="exibirDadosSemMascara()">Atualizar</button>
    <p>{{ resultado }}</p>
  </div>
</template>

<script>
//:mask="user.language === 'ptBR' ? '(##) ####-####' : '(###) ###-####'"
export default {
  name: "App",
  data() {
    //variaveis da intancia do objeto que representam dados mediante a interação do usuário
    return {
      cpf:'',
      data:'',
      cnpj:'',
      cpfCnpj:'',
      telefone:'',
      celular:'',
      telefoneCeluar:'',
      decimal:'',
      moeda:'',
      resultado:'',
      moneyMask: {
          decimal: ',',
          thousands: '.',
          prefix: 'R$ ',
          precision: 2
      }
    };
  },
  methods: {
      //criar uma função para conversão de data: 17/03/23 -> 2023/03/17
      //este método poderia ser um mixin no vue
      unmask(text) {
        const defaultDelimiters = /[-!$%^&*()_+|~=`{}[\]:";'<>?,./\\ ]/
        return text ? String(text).replace(new RegExp(defaultDelimiters, 'g'), '') : text
      },
      preencher(){
        this.cpf='34829826002';
        this.cnpj='98262897000126';
        this.telefone='1133870967';
        this.celular='11965450789';
        this.decimal=10527.6543;
        this.moeda=10527.6543;
      },
      limpar(){
        this.cpf=null;
        this.cnpj=null;
        this.telefone=null;
        this.celular=null;
        this.decimal=null;
        this.moeda=null;
      },
      exibirDadosSemMascara(){
        this.resultado = 'resultado....';
        const cpfRaw = this.unmask(this.cpf);
        this.resultado = `CPF: ${cpfRaw}, CNPJ ${this.unmask(this.cnpj)}, TELEFONE ${this.unmask(this.telefone)}, CELULAR ${this.unmask(this.celular)} , MOEDA ${this.unmask(this.moeda)}`;
      }
    },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

