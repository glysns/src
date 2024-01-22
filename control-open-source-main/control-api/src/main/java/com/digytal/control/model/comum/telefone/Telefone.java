package com.digytal.control.model.comum.telefone;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
@Schema(name = "Requisição do telefone",description="Pré requisitos para o meio de contato")
public class Telefone {
    @Schema(description="número para telefone fixo", minLength = 8, maxLength = 11,example = "43111208")
    @Column(name = "tel_fixo")
    private Long fixo;
    @Schema(description="número para telefone celular",minLength = 9,maxLength = 11,example = "11966540923")
    @Column(name = "tel_celular")
    private Long celular;
    @Schema(description="define se celular é WhatsApp", example = "true", allowableValues = {"true","false"})
    @Column(name = "tel_celular_whatsapp")
    private boolean celularWhatsApp;
}
