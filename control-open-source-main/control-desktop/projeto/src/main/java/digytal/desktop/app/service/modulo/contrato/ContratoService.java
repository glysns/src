package digytal.desktop.app.service.modulo.contrato;

import org.springframework.stereotype.Service;

import digytal.desktop.app.model.modulo.contrato.ContratoRequest;
import digytal.desktop.app.service.core.HttpClient;
import digytal.desktop.util.utils.business.BusinessException;
import digytal.desktop.util.utils.http.Response;

@Service
public class ContratoService extends HttpClient {
	public ContratoService() {
        super("contratos");
    }
	public Response gerarContratoVenda(ContratoRequest request) throws BusinessException {
        return post(request, "venda-servico").body();
    }

}
