package digytal.desktop.app.service.consulta.lancamento;

import java.util.List;

import org.springframework.stereotype.Service;

import digytal.desktop.app.model.consulta.filtro.TransacaoFiltro;
import digytal.desktop.app.model.modulo.financeiro.response.PagamentoResponse;
import digytal.desktop.app.model.modulo.financeiro.response.TransacaoResponse;
import digytal.desktop.app.service.core.HttpClient;

@Service
public class TransacaoConsultaService extends HttpClient {

	public TransacaoConsultaService() {
		super("/consultas/transacoes");
	}
	public List<TransacaoResponse> pesquisar(TransacaoFiltro filtro ){
		String params = params(filtro); 
        return get(params).list(TransacaoResponse.class);
    }
	public List<TransacaoResponse> pesquisarCompleta(TransacaoFiltro filtro ){
		String params = params(filtro); 
        return get("completa",params).list(TransacaoResponse.class);
    }

}
