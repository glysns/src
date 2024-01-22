package digytal.desktop.app.service.consulta.lancamento;

import java.util.List;

import org.springframework.stereotype.Service;

import digytal.desktop.app.model.consulta.filtro.LancamentoFiltro;
import digytal.desktop.app.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import digytal.desktop.app.model.modulo.financeiro.response.ParcelamentoResponse;
import digytal.desktop.app.model.modulo.financeiro.response.parcelamento.ParcelaResponse;
import digytal.desktop.app.service.core.HttpClient;

@Service
public class ParcelamentoConsultaService  extends HttpClient {
	public ParcelamentoConsultaService() {
		super("/consultas/parcelamentos");
	}
	public List<ParcelamentoResponse> pesquisarReceitas(LancamentoFiltro filtro ){
		filtro.setTipo(AplicacaoTipo.RECEITA);
		return pesquisar(filtro);
    }
	public List<ParcelamentoResponse> pesquisar(LancamentoFiltro filtro ){
		String params = params(filtro); 
        return get(params).list(ParcelamentoResponse.class);
    }
	public List<ParcelaResponse> consultarPacelas(Integer id ){
		return get(id,"parcelas").list(ParcelaResponse.class);
    }

}
