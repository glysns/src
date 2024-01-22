package digytal.desktop.app.model.consulta.filtro;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagamentoFiltro extends LancamentoFiltro {
	Integer conta;
}
