package digytal.desktop.app.model.modulo.financeiro.response.parcelamento;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ParcelaBoleto {
    private boolean solicitado;
    private ParcelaBoletoStatus status ;
    private String numeroAutorizacao;
    private String urlImpressao;
    private String linhaDigitavel;
    private Double valorCompensado;
    private LocalDate dataPagamento;
    private LocalDate dataCompensacao;

}