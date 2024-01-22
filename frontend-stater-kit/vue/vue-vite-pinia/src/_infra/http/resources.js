import {http} from './index';

export default class Resources {
    constructor(path) {
        this.path = '/' + path;
    }

    post(sufix, body){
        const config =  this.config({});
        const url = `${this.path}${sufix}`
        console.log('POST->', url);
        return http.post(url,body,config)
    }

    put(body){
        const { id } = body;
        const url = `${this.path}/${id}`

        console.log('PUT->', url);
        return http.put(url,body)
    }
    
    patch(id,sufix, body){
        const config =  this.config({});
        const url = `${this.path}/${id}${sufix}`
        console.log('PATCH->', url);
        return http.patch(url,body,config)
    }
    
    get(sufix, params) {
        const config =  this.config(params);
        return http.get(this.path+sufix,config)
    }
    
    delete(id){
        const url = `${this.path}/${id}`
        console.log('DELETE', url);
        return http.delete(url)
    }

    config(params){
        //** REMOVA SE NÃO PRECISAR */
        for (const key in params) {
            if (!params[key]) {
                delete params[key];
            }
        }
        
        //** REMOVA SE NÃO PRECISAR */
        const config={
            params:params,
            transformResponse:[function(data){
                const response = JSON.parse(data);
                //VC PODE CUSTOMIZAR
                return response
            }]
        }
        return config;
    }

}