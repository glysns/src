import Resources from "./resources";

class ClienteResource extends Resources {
    
    constructor() {
        super("clientes");
    }
    
    listar(): Promise<any> {
        return this.get('');
    }

    buscar(id: any): Promise<any> {
        return this.get(`/${id}`);
    }

    incluir(data: any): Promise<any> {
        return this.post('',data);
    }

    alterar(id: any, data: any): Promise<any> {
        return this.put(id, data);
    }

    excluir(id: any): Promise<any> {
        return this.delete(id);
    }
}

export const clienteResource = new ClienteResource()