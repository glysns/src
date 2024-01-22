import Resources from "./resources";

class ClienteResource extends Resources {
    constructor(){
        super('clientes')
    }

    listar(){
        return this.get('');
    }
    
    buscar(id){
        return this.get(`/${id}`);
    }

    incluir(cadastro) {
        return this.post('',cadastro);
    }
    
    alterar(cadastro) {
        return this.put(cadastro);
    }

    excluir(id) {
        return this.delete(id);
    }
}

export const clienteResource = new ClienteResource()