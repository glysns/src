package digytal.desktop.app.model.modulo.financeiro.response.parcelamento;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PacelaPendencia {
    private boolean atrasada;
    private Integer diasAtraso=0;
    private boolean negociada;
    private LocalDate dataProximoVencimento;
}
