package com.digytal.control.model.comum.recorrencia;

import com.digytal.control.infra.persistence.EnumConverter;
import com.digytal.control.infra.persistence.EnumerateId;

public enum Periodicidade implements EnumerateId {
    DIARIA("D","Diária",1),
    SEMANAL("S","Semanal",7),
    QUINZENAL("Q","Quinzenal",15),
    MENSAL("M","Mensal",1),
    BIMESTRAL("B","Bimestral",2),
    TRIMESTRAL("T","Trimestral",3),
    QUADRIMESTRAL("Q","Quadrimestral",4),
    SEMESTRAL("R","Semestral",6),
    ANUAL("A","Anual",1);
    private String id;
    private String descricao;
    private Integer dias;
    private Periodicidade(String id, String descricao, Integer dias){
        this.id = id;
        this.descricao=descricao;
        this.dias = dias;
    }

    public Integer getDias() {
        return dias;
    }

    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String getUpper() {
        return descricao.toUpperCase();
    }

    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends EnumConverter<Periodicidade, String> {
        public Converter() {
            super(Periodicidade.class);
        }
    }
}
