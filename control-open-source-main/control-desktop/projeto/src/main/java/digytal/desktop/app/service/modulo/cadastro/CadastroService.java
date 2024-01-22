package digytal.desktop.app.service.modulo.cadastro;


import java.util.List;

import org.springframework.stereotype.Service;

import digytal.desktop.app.model.modulo.cadastro.CadastroRequest;
import digytal.desktop.app.model.modulo.cadastro.CadastroResponse;
import digytal.desktop.app.model.modulo.comum.cadastramento.CadastroTipo;
import digytal.desktop.app.service.core.HttpClient;
import digytal.desktop.util.utils.business.BusinessException;
import digytal.desktop.util.utils.http.Response;

@Service
public class CadastroService extends HttpClient  {
	public CadastroService() {
        super("cadastros");
    }

	public Response alterar(Integer id, CadastroRequest request) throws BusinessException {
        return put(request,id).body();
    }
	public Response incluir(CadastroRequest request) throws BusinessException {
        return post(request).body();
    }
	public List<CadastroResponse> consultarClientes(String nome ){
        return get("clientes","nome", nome).list(CadastroResponse.class);
    }
	public CadastroResponse buscar(String cpfCnpj ){
        return get("cpf-cnpj", cpfCnpj).one(CadastroResponse.class);
    }
	public List<CadastroResponse> consultarFornecedores(String nome ){
        return get("fornecedores","nome", nome).list(CadastroResponse.class);
    }
	public List<CadastroResponse> consultar(CadastroTipo tipo, String nome){
        return get("tipo", tipo,"nome", nome).list(CadastroResponse.class);
    }
    
}
