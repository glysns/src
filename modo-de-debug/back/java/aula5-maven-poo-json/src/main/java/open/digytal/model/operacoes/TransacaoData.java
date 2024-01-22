package open.digytal.model.operacoes;

import java.util.Date;

public class TransacaoData {
    private Date dataHora;
    private Date dia;
    private Integer mes;
    private Integer ano;
    private Long competencia;

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Long getCompetencia() {
        return competencia;
    }

    public void setCompetencia(Long competencia) {
        this.competencia = competencia;
    }
}
