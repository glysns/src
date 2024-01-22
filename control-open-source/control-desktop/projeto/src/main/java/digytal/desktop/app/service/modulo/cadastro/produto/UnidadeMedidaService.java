package digytal.desktop.app.service.modulo.cadastro.produto;

import java.util.List;

import org.springframework.stereotype.Service;

import digytal.desktop.app.model.modulo.cadastro.produto.unidademedida.UnidadeMedidaRequest;
import digytal.desktop.app.model.modulo.cadastro.produto.unidademedida.UnidadeMedidaResponse;
import digytal.desktop.app.model.modulo.comum.Associacao;
import digytal.desktop.app.service.core.HttpClient;
import digytal.desktop.util.utils.business.BusinessException;
import digytal.desktop.util.utils.http.Response;

@Service
public class UnidadeMedidaService extends HttpClient{
	public UnidadeMedidaService() {
        super("unidades-medida");
    }
	public List<UnidadeMedidaResponse> consultar(String nome ){
        return get("nome", nome).list(UnidadeMedidaResponse.class);
    }
	public List<Associacao> listar(String nome){
        return get("listagem", nome).list(Associacao.class);
    }
	public Response incluir(UnidadeMedidaRequest request) throws BusinessException {
        return post(request).body();
    }
	public Response alterar(Integer id, UnidadeMedidaRequest request) throws BusinessException {
        return put(request, id).body();
    }
}
