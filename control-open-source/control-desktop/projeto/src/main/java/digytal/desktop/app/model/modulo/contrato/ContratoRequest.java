package digytal.desktop.app.model.modulo.contrato;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import digytal.desktop.app.model.modulo.financeiro.request.FormaPagamentoRequest;
import lombok.Data;

@Data
public class ContratoRequest {
	private LocalDate data;
	private String descricao;
	private Integer cadastro;
	private Integer intermediador;
	private Double valorAplicado=0.0;
	private Double valorDescontoManual=0.0;
	private List<ContratoItemRequest> itens = new ArrayList<ContratoItemRequest>();
	private List<FormaPagamentoRequest> formasPagamento = new ArrayList<FormaPagamentoRequest>();
}
