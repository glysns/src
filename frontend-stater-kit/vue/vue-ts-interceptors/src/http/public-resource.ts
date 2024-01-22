import Resources from "./resources";

class PublicResource extends Resources {
    
    constructor() {
        super("public");
    }
    logar(login: any): Promise<any> {
        return this.post('/login',login);
    }
}

export const publicResource = new PublicResource()