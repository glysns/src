package com.digytal.control.model.modulo.financeiro.parcelamento;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Data
@Embeddable
public class ParcelamentoDetalhe {
    @Column(name = "num_parcela")
    private Integer numeroParcela=0;
    @Column(name = "dt_vencto")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataVencimento=LocalDate.now();
    @Column(name = "vl_original")
    private Double valorOriginal=0.0;
    @Column(name = "vl_multa")
    private Double valorMulta=0.0;
    @Column(name = "vl_juros")
    private Double valorJuros=0.0;
    @Column(name = "vl_correcao")
    private Double valorCorrecao=0.0;
    @Column(name = "vl_desconto")
    private Double valorDesconto=0.0;
    @Column(name = "vl_amortizado")
    private Double valorAmortizado=0.0;
    @Column(name = "vl_atual")
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
