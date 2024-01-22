package digytal.desktop.app.service.modulo.cadastro.produto;

import java.util.List;

import org.springframework.stereotype.Service;

import digytal.desktop.app.model.modulo.cadastro.produto.modelo.ModeloRequest;
import digytal.desktop.app.model.modulo.cadastro.produto.modelo.ModeloResponse;
import digytal.desktop.app.model.modulo.comum.Associacao;
import digytal.desktop.app.service.core.HttpClient;
import digytal.desktop.util.utils.business.BusinessException;
import digytal.desktop.util.utils.http.Response;

@Service
public class ModeloService extends HttpClient{
	public ModeloService() {
        super("modelos");
    }
	public List<ModeloResponse> consultar(String nome ){
        return get("nome", nome).list(ModeloResponse.class);
    }
	public List<Associacao> listar(String nome){
        return get("listagem", nome).list(Associacao.class);
    }
	public Response incluir(ModeloRequest request) throws BusinessException {
        return post(request).body();
    }
	public Response alterar(Integer id, ModeloRequest request) throws BusinessException {
        return put(request, id).body();
    }
}
