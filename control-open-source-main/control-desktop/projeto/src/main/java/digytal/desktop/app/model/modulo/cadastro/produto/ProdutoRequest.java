package digytal.desktop.app.model.modulo.cadastro.produto;

import lombok.Data;

@Data
public class ProdutoRequest extends Produto {
	private Integer unidadeEmbalagem;
    private Integer unidadeMedida;;

    private Integer marca;
    private Integer modelo;
    private Integer categoria;
}
