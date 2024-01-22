package digytal.desktop.app.model.modulo.cadastro.produto;

import lombok.Data;

@Data
public class Produto {
    private String nome;
    private String nomeAbreviado;
    private String descricao;

    private Double valor;

    private Double taxaLiquidacao;

    private boolean atualizaSaldo;
    private boolean interno;
    private boolean servico;

    private String codigoBarras;
    private String sku;
}