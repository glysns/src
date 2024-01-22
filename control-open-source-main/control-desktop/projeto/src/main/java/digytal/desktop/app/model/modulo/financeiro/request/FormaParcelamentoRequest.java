package digytal.desktop.app.model.modulo.financeiro.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class FormaParcelamentoRequest {
	private Double valorParcela = 0.0;
	private Integer numeroParcelas = 1;
	private LocalDate dataPrimeiroVencimento;
}
