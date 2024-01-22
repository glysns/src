package com.digytal.control.model.modulo.contrato;

import com.digytal.control.infra.persistence.EnumConverter;
import com.digytal.control.infra.persistence.EnumerateId;
import com.digytal.control.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;

public enum ContratoTipo implements EnumerateId {
    COMPRA("C","Compra"){
        @Override
        public AplicacaoTipo getAplicacao() {
            return AplicacaoTipo.DESPESA;
        }
    },
    VENDA("V","Venda"),
    LOCACAO("L","Locação"),
    SERVICO("S","Serviço");

    private String id;
    private String descricao;
    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
    private ContratoTipo(String id,String descricao){
        this.id = id;
        this.descricao=descricao;
    }
    public AplicacaoTipo getAplicacao(){
        return AplicacaoTipo.RECEITA;
    }

    @Override
    public String getUpper() {
        return descricao.toUpperCase();
    }

    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends EnumConverter<ContratoTipo, String> {
        public Converter() {
            super(ContratoTipo.class);
        }
    }
}
