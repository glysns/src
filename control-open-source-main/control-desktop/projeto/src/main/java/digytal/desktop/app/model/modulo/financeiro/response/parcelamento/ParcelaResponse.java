package digytal.desktop.app.model.modulo.financeiro.response.parcelamento;

import lombok.Data;

@Data
public class ParcelaResponse {
    private Integer id;
    private String descricao;
    private ParcelamentoDetalhe detalhe = new ParcelamentoDetalhe();
    private ParcelaAliquota aliquota = new ParcelaAliquota();
    private PacelaPendencia pendencia = new PacelaPendencia();
    private ParcelaQuitacao quitacao = new ParcelaQuitacao();
    private ParcelaBoleto boleto = new ParcelaBoleto();
    private Integer parcelamento;
    private String observacao;
}
