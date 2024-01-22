package digytal.desktop.app.service.consulta.lancamento;

import java.util.List;

import org.springframework.stereotype.Service;

import digytal.desktop.app.model.consulta.filtro.PagamentoFiltro;
import digytal.desktop.app.model.modulo.financeiro.response.PagamentoResponse;
import digytal.desktop.app.service.core.HttpClient;

@Service
public class PagamentoConsultaService extends HttpClient {

	public PagamentoConsultaService() {
		super("/consultas/pagamentos");
	}
	public List<PagamentoResponse> listarPagamentos(PagamentoFiltro filtro ){
		String params = params(filtro); 
        return get(params).list(PagamentoResponse.class);
    }

}
