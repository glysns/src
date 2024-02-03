<template>
    <div>
        <h2>
            <NuxtLink to="/eventos"> Voltar </NuxtLink>
        </h2>
        <h1>/pages/eventos/[ID].vue</h1>
        <h2>Evento Escolhido: {{ $route.params.id }}</h2>
        <h2>Evento: {{ evento?.id }}</h2>
        <p>
            {{ evento }}
        </p>

        <p />

        <form>
            <input v-model="cadastro.nome" type="text" id="nome" name="nome" placeholder="Nome Completo" />
            <input v-model="cadastro.email" type="text" id="email" name="email" placeholder="email@email.com" />
            <input v-model="cadastro.cpf" type="text" id="cpf" name="cpf" placeholder="006.865.778-09" />
            <input v-model="cadastro.observacao" type="text" id="observacao" name="observacao"
                placeholder="Entregar em: Rua das Marias, 375 - Teresina PI" />

            <h3 v-if="waiting">Processando ... </h3>
            <button @click.prevent="confirmar()">Confirmar</button>
        </form>

        <div class="row">
            <div class="col-lg-6">
                <div class="card">
                    <div class="card-header d-flex justify-content-between">
                        <div class="header-title">
                            <h4 class="card-title">Account Setting</h4>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="acc-edit">
                            <form>
                                <div class="form-group">
                                    <label for="uname">Nome:</label>
                                    <input type="text" class="form-control" id="uname" v-model="cadastro.nome">
                                </div>
                                <div class="form-group">
                                    <label for="email">Email:</label>
                                    <input type="email" class="form-control" id="email" v-model="cadastro.email">
                                </div>
                                <div class="form-group">
                                    <label for="uname">CPF:</label>
                                    <input type="text" class="form-control" id="uname" v-model="cadastro.cpf">
                                </div>
                               
                              
                                <button class="btn btn-primary" >Submit</button>
                                <button  class="btn iq-bg-danger">Cancel</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
           
        </div>
    </div>
</template>

<script setup>
//https://rafamed.dev/blog/how-to-add-form-validation-to-your-nuxt-3-application
const { evento, waiting, confirmarReserva } = useReservaService();
const cadastro = ref({
    nome: "Gleyson Sampaio 2",
    cpf: "19223219078",
    email: "gleyson.me@gmail.com",
    observacao: "Rua das Marias, 375 - Teresina PI",
});
const confirmar = async () => {
    const payload = cadastro.value;
    if (!payload.email) {
        alert('Email é obrigatório')
        return
    }
    console.log('cadastro preenchido', cadastro.value)
    const { status, body } = await confirmarReserva(cadastro)
    console.log('status', status);
    console.log('body', body);

}
</script>
  