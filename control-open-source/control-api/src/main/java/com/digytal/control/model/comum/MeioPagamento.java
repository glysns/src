package com.digytal.control.model.comum;

import com.digytal.control.infra.persistence.EnumConverter;
import com.digytal.control.infra.persistence.EnumerateId;

public enum MeioPagamento implements EnumerateId {
    DINHEIRO("A","Dinheiro",true),
    PIX("X","Pix",true),
    DEBITO("D","Débito",true),
    BOLETO("B","Boleto",false),
    CREDITO("C","Crédito",false),
    PRAZO("Z","Prazo",false),
    SALDO ("Y","Saldo",true),
    COMPENSACAO("W","Compensação", true)
    ;
    private String id;
    private String descricao;
    private boolean instantaneo;
    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
    private MeioPagamento(String id, String descricao, boolean instantaneo){
        this.id = id;
        this.descricao=descricao;
        this.instantaneo=instantaneo;
    }

    public boolean isInstantaneo() {
        return instantaneo;
    }

    @Override
    public String getUpper() {
        return descricao.toUpperCase();
    }

    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends EnumConverter<MeioPagamento, String> {
        public Converter() {
            super(MeioPagamento.class);
        }
    }
}
