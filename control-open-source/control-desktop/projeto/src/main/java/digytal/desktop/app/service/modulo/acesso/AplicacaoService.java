package digytal.desktop.app.service.modulo.acesso;

import java.util.List;

import org.springframework.stereotype.Service;

import digytal.desktop.app.model.modulo.acesso.empresa.aplicacao.AplicacaoRequest;
import digytal.desktop.app.model.modulo.acesso.empresa.aplicacao.AplicacaoResponse;
import digytal.desktop.app.service.core.HttpClient;
import digytal.desktop.util.utils.business.BusinessException;
import digytal.desktop.util.utils.http.Response;

@Service
public class AplicacaoService extends HttpClient {

	public AplicacaoService() {
		super("aplicacoes");
		
	}
	public Response <Integer> incluirArea(AplicacaoRequest request) throws BusinessException {
        return post(request,"areas").body();
    }
	public Response <Integer> incluirReceita(AplicacaoRequest request) throws BusinessException {
        return post(request,"receitas").body();
    }
	public Response <Boolean> alterarNome(Integer id, String nome) throws BusinessException {
        return patch(null,id,"nome",nome).body();
    }
	public Response <Integer> incluirDespesa(AplicacaoRequest request) throws BusinessException {
        return post(request,"despesas").body();
    }
	public List<AplicacaoResponse> listarAreas(String nome ){
        return get("areas").list(AplicacaoResponse.class);
    }
	public List<AplicacaoResponse> listarReceitas(String nome ){
        return get("receitas").list(AplicacaoResponse.class);
    }
	public List<AplicacaoResponse> listarDespesas(String nome ){
        return get("despesas").list(AplicacaoResponse.class);
    }

}
