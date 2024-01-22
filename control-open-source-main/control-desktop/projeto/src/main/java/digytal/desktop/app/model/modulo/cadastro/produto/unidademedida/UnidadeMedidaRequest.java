package digytal.desktop.app.model.modulo.cadastro.produto.unidademedida;

import lombok.Data;

@Data
public class UnidadeMedidaRequest {
	private String nome;
    private String sigla;
    private String descricao;
    private Double conteudo;
    private boolean embalagem;
}
