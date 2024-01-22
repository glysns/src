package digytal.desktop.app.model.modulo.financeiro.request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import digytal.desktop.app.model.modulo.financeiro.Transacao;
import lombok.Data;

@Data
public class TransacaoRequest extends Transacao {
	private LocalDate data;
	private Double valor;
	private Integer area;
	private Integer natureza;
	private Integer cadastro;
	private List<FormaPagamentoRequest> formasPagamento = new ArrayList<FormaPagamentoRequest>();
}
