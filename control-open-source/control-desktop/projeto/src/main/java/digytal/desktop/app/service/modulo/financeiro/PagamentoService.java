package digytal.desktop.app.service.modulo.financeiro;

import org.springframework.stereotype.Service;

import digytal.desktop.app.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import digytal.desktop.app.model.modulo.financeiro.request.TransacaoRequest;
import digytal.desktop.app.service.core.HttpClient;
import digytal.desktop.util.utils.business.BusinessException;
import digytal.desktop.util.utils.http.Response;

@Service
public class PagamentoService extends HttpClient {
	public PagamentoService() {
        super("transacoes");
    }
	public Response inserir(AplicacaoTipo tipo, TransacaoRequest request) throws BusinessException {
        return post(request,tipo.name().toLowerCase()).body();
    }
	
}
