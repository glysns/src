package digytal.desktop.app.service.modulo.acesso.conta;

import java.util.List;

import org.springframework.stereotype.Service;

import digytal.desktop.app.model.modulo.acesso.empresa.conta.ContaRequest;
import digytal.desktop.app.model.modulo.acesso.empresa.conta.ContaResponse;
import digytal.desktop.app.model.modulo.acesso.empresa.pagamento.FormaPagamentoCadastroRequest;
import digytal.desktop.app.model.modulo.acesso.empresa.pagamento.FormaPagamentoCadastroResponse;
import digytal.desktop.app.model.modulo.comum.MeioPagamento;
import digytal.desktop.app.service.core.HttpClient;
import digytal.desktop.util.utils.business.BusinessException;
import digytal.desktop.util.utils.http.Response;

@Service
public class ContaService extends HttpClient {
	public ContaService() {
        super("contas");
    }

	public Response <Integer> incluirFormaPagamento(Integer conta, FormaPagamentoCadastroRequest request) throws BusinessException {
        return post(request,conta,"formas-pagamento").body();
    }
	public Response <Boolean> excluirFormaPagamento(Integer id) throws BusinessException {
        return delete("formas-pagamento",id).body();
    }
   
    public List<FormaPagamentoCadastroResponse> listarFormasPagamento(Integer id ){
        return get(id,"formas-pagamento").list(FormaPagamentoCadastroResponse.class);
    }
    public List<ContaResponse> listar( ){
        return get().list(ContaResponse.class);
    }
    
    public Response <Integer> incluir(ContaRequest request) throws BusinessException {
        return post(request).body();
    }
    public Response <Integer> alterar(Integer id,ContaRequest request) throws BusinessException {
        return put(request,id).body();
    }
    public FormaPagamentoCadastroResponse localizarFormaPagamento(MeioPagamento meioPagamento, Integer numeroParcelas) {
    	return get("formas-pagamento", meioPagamento, numeroParcelas).one(FormaPagamentoCadastroResponse.class);
    }
    public List<FormaPagamentoCadastroResponse> consultarFormasPagamento(MeioPagamento meioPagamento) {
    	return get("formas-pagamento", meioPagamento).list(FormaPagamentoCadastroResponse.class);
    }
    
}
