<template>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">                
                <div class="event-content">
                    <div id="event1" class="tab-pane fade active show">
                        <div class="row">                                
                            <div class="col-lg-4 col-md-6" v-for="i in eventos" :key="i.tag">
                                <div class="card card-block card-stretch card-height">
                                    <div class="card-body rounded event-detail event-detail-success">
                                        <div class="d-flex align-items-top justify-content-between">
                                            <div>
                                                <h4 class="mb-2 mr-4">{{i.id}}</h4> 
                                                <h4 class="mb-2 mr-4">{{i.titulo}}</h4>
                                                <h4 class="mb-2 mr-4">{{i.categoria}}</h4>
                                                <h4 class="mb-2 mr-4">{{i.data}}</h4>
                                                <h4 class="mb-2 mr-4">{{i.hora}}</h4>
                                                <h4 class="mb-2 mr-4">{{i.duracao}}</h4>
                                                <h4 class="mb-2 mr-4">{{i.numeroDisponiveis}}/{{i.numeroVagas}}</h4>                                                 
                                                <h6 class="mb-2 mr-4">{{i.tag}}</h6> 
                                                
                                                <p class="mb-2 text-success font-weight-500 text-uppercase"><i class="las la-user-friends pr-2"></i>Em Grupo</p>
                                                <p class="mb-4 card-description">{{i.descricao}}</p>
                                                <div class="d-flex align-items-center pt-4">
                                                    <a href="#" class="btn btn-success mr-3 px-xl-4">{{i.valor}}</a>
                                                    <a href="#" class="btn btn-outline-success copy px-xl-4" data-extra-toggle="copy" title="Copy to clipboard" data-toggle="tooltip"><i class="las la-link pr-2"></i>Reservar</a>
                                                    <a href="#" class="btn btn-outline-success d-none turn-on px-xl-4">Turn On</a>
                                                </div>
                                            </div>
                                           
                                        </div>
                                    </div>
                                </div>
                            </div>                                
                        </div>
                    </div>
                </div>
            </div>
        </div>


    </div>
</template>
<script setup>
// Comada de Service
const {listEventos} = usePublicApi();
// 1 Linha para consultar uma api e exibir e jogar em uma variável 
const {body:eventos} = await listEventos();
// Transportando um objeto de uma pagina para outra 
const { selecionarEvento } = useReservaService();
</script>
