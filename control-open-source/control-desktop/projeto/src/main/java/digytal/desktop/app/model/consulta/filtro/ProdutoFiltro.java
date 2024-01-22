package digytal.desktop.app.model.consulta.filtro;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProdutoFiltro {
	private String nome;
	private Integer categoria;
	private Integer marca;
	private Integer modelo;
}
