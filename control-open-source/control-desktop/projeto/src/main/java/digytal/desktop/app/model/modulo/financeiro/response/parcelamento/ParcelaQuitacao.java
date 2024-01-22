package digytal.desktop.app.model.modulo.financeiro.response.parcelamento;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ParcelaQuitacao {
    private boolean efetuada;
    private LocalDate data;
}
