import axios from 'axios';

export const http = axios.create({
    baseURL:"http://localhost:8080"
})

http.interceptors.response.use(function (res) {
    //normalizando a minha response para o meu contexto de negócio
    return response(res);
   
}, function (error) {
    //quando houver erro eu pego a resposta do erro
    return Promise.resolve(response( error.response));
});

const response = function(res){
    const response =
        {
            baseURL:res.config?.baseURL,
            url:res.config?.url,
            data:res.data?.body,
            status:res.data?.status
        }
    return response;
}


//** DESCOMENTE SE PRECISAR */

//PARA APIS QUE POSSUEM ADAPTAÇÃO ESTRUTURAL NAS REQUISIÇÕES
//COMO VALIDAR USO DE TOKEN

/*

ROOT_API.interceptors.request.use(function(config){
    return config;
})

*/

//** DESCOMENTE SE PRECISAR */

//PARA APIS QUE POSSUEM ADAPTAÇÃO ESTRUTURAL NAS RESPOTAS
//COMO UMA RESPOSTA CUSTOMIZADA

/*
ROOT_API.interceptors.response.use(function (res) {
    //normalizando a minha response para o meu contexto de negócio
    return response(res);
   
}, function (error) {
    //quando houver erro eu pego a resposta do erro
    return Promise.resolve(response( error.response));
});

*/


//** DESCOMENTE SE PRECISAR */

/*
const response = function(res){
    const response =
        {
            baseURL:res.config?.baseURL,
            url:res.config?.url,
            data:res.data?.body,
            message:res.data?.message,
            success:res.data?.success
        }
    return response;
}
*/

