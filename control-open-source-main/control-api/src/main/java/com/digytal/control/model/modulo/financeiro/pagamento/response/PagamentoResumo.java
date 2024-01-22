package com.digytal.control.model.modulo.financeiro.pagamento.response;

import java.util.List;
import lombok.Data;

@Data
public class PagamentoResumo {
	private List<PagamentoResponse> pagamentos;
	private Double saldo;
	private Double totalReceitas;
	private List<PagamentoResponse> receitas;
	private Double totalDespesas;
	private List<PagamentoResponse> despesas;
}
