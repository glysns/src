package digytal.desktop.app.service.modulo.cadastro.produto;

import java.util.List;

import org.springframework.stereotype.Service;

import digytal.desktop.app.model.consulta.filtro.ProdutoFiltro;
import digytal.desktop.app.model.modulo.cadastro.produto.ProdutoRequest;
import digytal.desktop.app.model.modulo.cadastro.produto.ProdutoResponse;
import digytal.desktop.app.service.core.HttpClient;
import digytal.desktop.util.utils.business.BusinessException;
import digytal.desktop.util.utils.http.Response;

@Service
public class ProdutoService extends HttpClient{
	public ProdutoService() {
        super("produtos");
    }
	public List<ProdutoResponse> listar(Integer organizacao,boolean comInternos, String nome ){
        return get("organizacao", organizacao,"com-internos", comInternos, "nome", nome).list(ProdutoResponse.class);
    }
	public ProdutoResponse buscar(Integer id ){
        return get(id).one(ProdutoResponse.class);
    }
	public List<ProdutoResponse> consultar(String nome ){
        return get("nome", nome).list(ProdutoResponse.class);
    }
	public List<ProdutoResponse> pesquisar(ProdutoFiltro filtro ){
		String params = params(filtro); 
        return get("pesquisa",params).list(ProdutoResponse.class);
    }
	public Response incluir(ProdutoRequest request) throws BusinessException {
        return post(request).body();
    }
	public Response alterar(Integer id, ProdutoRequest request) throws BusinessException {
        return put(request, id).body();
    }
}
