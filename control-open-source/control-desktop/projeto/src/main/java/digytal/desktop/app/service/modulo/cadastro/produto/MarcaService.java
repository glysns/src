package digytal.desktop.app.service.modulo.cadastro.produto;

import java.util.List;

import org.springframework.stereotype.Service;

import digytal.desktop.app.model.modulo.cadastro.produto.marca.MarcaRequest;
import digytal.desktop.app.model.modulo.cadastro.produto.marca.MarcaResponse;
import digytal.desktop.app.model.modulo.comum.Associacao;
import digytal.desktop.app.service.core.HttpClient;
import digytal.desktop.util.utils.business.BusinessException;
import digytal.desktop.util.utils.http.Response;

@Service
public class MarcaService extends HttpClient{
	public MarcaService() {
        super("marcas");
    }
	public List<MarcaResponse> consultar(String nome ){
        return get("nome", nome).list(MarcaResponse.class);
    }
	public List<Associacao> listar(String nome){
        return get("listagem", nome).list(Associacao.class);
    }
	public Response incluir(MarcaRequest request) throws BusinessException {
        return post(request).body();
    }
	public Response alterar(Integer id, MarcaRequest request) throws BusinessException {
        return put(request, id).body();
    }
}
