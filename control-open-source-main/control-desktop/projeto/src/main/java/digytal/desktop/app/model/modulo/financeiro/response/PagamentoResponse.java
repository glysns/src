package digytal.desktop.app.model.modulo.financeiro.response;

import digytal.desktop.app.model.modulo.financeiro.TransacaoValor;
import lombok.Data;

@Data
public class PagamentoResponse extends LancamentoResponse {
	 private TransacaoValor valor;
	
}
