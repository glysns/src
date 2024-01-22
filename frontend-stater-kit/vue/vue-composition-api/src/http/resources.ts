import http from "./index";

export default class Resources {
    path! : string;

    constructor(sufix:string) {
        this.path = '/' + sufix;
    }

    get(sufix:string): Promise<any> {
        const url = `${this.path}${sufix}`
        console.log('GET->', url);
        return http.get(url);
    }
    post(sufix:string, body:object){
        const url = `${this.path}${sufix}`
        console.log('POST->', url);
        return http.post(url,body)
    }
    put(id:number,body:object){
        const url = `${this.path}/${id}`
        console.log('PUT->', url);
        return http.put(url,body)
    }
    
    patch(id:number,sufix:string, body:object){
        const url = `${this.path}/${id}${sufix}`
        console.log('PATCH->', url);
        return http.patch(url,body)
    }
    delete(id:number){
        const url = `${this.path}/${id}`
        console.log('DELETE', url);
        return http.delete(url)
    }
}