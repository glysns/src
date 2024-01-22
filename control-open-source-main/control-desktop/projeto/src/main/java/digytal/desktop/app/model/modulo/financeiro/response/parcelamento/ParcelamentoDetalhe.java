package digytal.desktop.app.model.modulo.financeiro.response.parcelamento;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ParcelamentoDetalhe {
    private Integer numeroParcela=0;
    private LocalDate dataVencimento=LocalDate.now();
    private Double valorOriginal=0.0;
    private Double valorMulta=0.0;
    private Double valorJuros=0.0;
    private Double valorCorrecao=0.0;
    private Double valorDesconto=0.0;
    private Double valorAmortizado=0.0;
    private Double valorAtual=0.0;
    public static ParcelamentoDetalhe of(Integer numeroParcela, Double valor, LocalDate dataVencimento){
        ParcelamentoDetalhe instance = new ParcelamentoDetalhe();
        instance.setValorOriginal(valor);
        instance.setValorAtual(valor);
        instance.setNumeroParcela(numeroParcela);
        instance.setDataVencimento(dataVencimento);
        return instance;
    }
}
