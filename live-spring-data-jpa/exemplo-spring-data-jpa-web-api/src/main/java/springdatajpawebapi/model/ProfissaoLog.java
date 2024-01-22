package springdatajpawebapi.model;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class ProfissaoLog {
    private LocalDateTime dataHoraCriacao;
    private LocalDateTime dataHoraAlteracao;

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public LocalDateTime getDataHoraAlteracao() {
        return dataHoraAlteracao;
    }

    public void setDataHoraAlteracao(LocalDateTime dataHoraAlteracao) {
        this.dataHoraAlteracao = dataHoraAlteracao;
    }
}
