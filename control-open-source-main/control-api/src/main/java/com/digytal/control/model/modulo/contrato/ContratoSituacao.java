package com.digytal.control.model.modulo.contrato;

import com.digytal.control.infra.persistence.EnumConverter;
import com.digytal.control.infra.persistence.EnumerateId;

public enum ContratoSituacao implements EnumerateId {
    SOLICITADO("S","Solicitado"),
    PROPOSTA("P","Proposta"),//EMPRESA PRA CLIENTE
    RESERVA("V","Reserva"), //CLIENTE PRA EMPRESA
    ELABORANDO("E","Elaborando"),
    ANALISANDO("A","Analisando"),
    CONCLUIDO("C","Concluido"),
    BLOQUEADO("B","Bloqueado"),
    ANULADO("U","Anulado"), //QUEM EMITE
    REJEITADO("R","Rejeitado"), //QUEM RECEBE
    ENCERRADO("N","Encerrado"),
    ;
    private String id;
    private String descricao;
    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
    private ContratoSituacao(String id,String descricao){
        this.id = id;
        this.descricao=descricao;
    }

    @Override
    public String getUpper() {
        return descricao.toUpperCase();
    }

    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends EnumConverter<ContratoSituacao, String> {
        public Converter() {
            super(ContratoSituacao.class);
        }
    }
}
