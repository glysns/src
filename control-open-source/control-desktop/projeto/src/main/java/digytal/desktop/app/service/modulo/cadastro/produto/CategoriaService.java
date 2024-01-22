package digytal.desktop.app.service.modulo.cadastro.produto;

import java.util.List;

import org.springframework.stereotype.Service;

import digytal.desktop.app.model.modulo.cadastro.produto.categoria.CategoriaRequest;
import digytal.desktop.app.model.modulo.cadastro.produto.categoria.CategoriaResponse;
import digytal.desktop.app.model.modulo.comum.Associacao;
import digytal.desktop.app.service.core.HttpClient;
import digytal.desktop.util.utils.business.BusinessException;
import digytal.desktop.util.utils.http.Response;

@Service
public class CategoriaService extends HttpClient{
	public CategoriaService() {
        super("categorias");
    }
	public List<CategoriaResponse> consultar(String nome ){
        return get("nome", nome).list(CategoriaResponse.class);
    }
	public List<Associacao> listar(String nome){
        return get("listagem", nome).list(Associacao.class);
    }
	public Response incluir(CategoriaRequest request) throws BusinessException {
        return post(request).body();
    }
	public Response alterar(Integer id, CategoriaRequest request) throws BusinessException {
        return put(request, id).body();
    }
}
