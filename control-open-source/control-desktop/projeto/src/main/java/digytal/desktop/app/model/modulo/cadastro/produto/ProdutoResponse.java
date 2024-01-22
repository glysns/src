package digytal.desktop.app.model.modulo.cadastro.produto;

import digytal.desktop.app.model.modulo.comum.Associacao;
import lombok.Data;

@Data
public class ProdutoResponse extends Produto { 
    private Integer id;
    private Double saldo;
    private Associacao marca;
    private Associacao modelo;
    private Associacao categoria;
    private Associacao unidadeMedida;
}
