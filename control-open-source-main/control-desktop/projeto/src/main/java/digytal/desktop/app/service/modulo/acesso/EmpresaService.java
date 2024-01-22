package digytal.desktop.app.service.modulo.acesso;

import java.util.List;

import org.springframework.stereotype.Service;

import digytal.desktop.app.model.modulo.acesso.empresa.EmpresaRequest;
import digytal.desktop.app.model.modulo.acesso.empresa.EmpresaResponse;
import digytal.desktop.app.model.modulo.acesso.empresa.EmpresaSimplificadaResponse;
import digytal.desktop.app.service.core.HttpClient;
import digytal.desktop.util.utils.business.BusinessException;
import digytal.desktop.util.utils.http.Response;

@Service
public class EmpresaService extends HttpClient  {
	public EmpresaService() {
        super("empresas");
    }

    public List<EmpresaSimplificadaResponse> listarContas(Integer id ){
        return get("vinculadas").list(EmpresaSimplificadaResponse.class);
    }
    
    public EmpresaResponse buscar(Integer id ){
        return get(id).one(EmpresaResponse.class);
    }
    
    public Response alterar(Integer id, EmpresaRequest request) throws BusinessException {
        return put(request,id).body();
    }
	public Response incluir(EmpresaRequest request) throws BusinessException {
        return post(request).body();
    }
	public Response alterarAsaasToken(String token) throws BusinessException {
        return patch(null,"asaas","token",token).body();
    }
	public Response alterarAsaasWebhookToken(String token) throws BusinessException {
        return patch(null,"asaas","webhook-token",token).body();
    }
	public Response alterarAsaasTaxaBoleto(Double taxaBoleto) throws BusinessException {
		return patch(null,"asaas","taxa-boleto",taxaBoleto).body();
    }
}
