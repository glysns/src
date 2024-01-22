package com.digytal.control.model.modulo.acesso.empresa.aplicacao;

import com.digytal.control.infra.persistence.EnumConverter;
import com.digytal.control.infra.persistence.EnumerateId;

public enum AplicacaoTipo implements EnumerateId {
    RECEITA("R","Receita"),
    DESPESA("D","Despesa");
    private String id;
    private String descricao;
    private AplicacaoTipo(String id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }
    public String getId() {
        return id;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public String getUpper() {
        return descricao.toUpperCase();
    }

    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends EnumConverter<AplicacaoTipo, String> {
        public Converter() {
            super(AplicacaoTipo.class);
        }
    }
}
