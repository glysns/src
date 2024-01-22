package com.digytal.control.model.modulo.financeiro.parcelamento.boleto;

import com.digytal.control.infra.persistence.EnumConverter;
import com.digytal.control.infra.persistence.EnumerateId;

import java.util.Arrays;

public enum ParcelaBoletoStatus implements EnumerateId {
    SOLICITADO("S","Solicitado"),
    EMITIDO("E","Emitido"),
    PAGO ("P","Pago"),
    RECUSADO("R","Recusado"),
    CANCELADO("C","Cancelado");
    private String id;
    private String descricao;

    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
    private ParcelaBoletoStatus(String id, String descricao){
        this.id = id;
        this.descricao=descricao;

    }
    @Override
    public String getUpper() {
        return descricao.toUpperCase();
    }

    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends EnumConverter<ParcelaBoletoStatus, String> {
        public Converter() {
            super(ParcelaBoletoStatus.class);
        }
    }
    public static ParcelaBoletoStatus find(String id){
        return  Arrays.stream(ParcelaBoletoStatus.class.getEnumConstants()).filter(i-> i.id.equals(id.toUpperCase()))
                .findFirst()
                .orElse(null);
    }
}
