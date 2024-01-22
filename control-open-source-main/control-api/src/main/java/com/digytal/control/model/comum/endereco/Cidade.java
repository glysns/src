package com.digytal.control.model.comum.endereco;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@Schema(name = "Requisição de cidade",description="Pré requisitos de cidade para o cadastro")
public class Cidade {
    @Schema(description="nome da cidade", maxLength = 70, example = "SAO PAULO")
    @Column(name = "end_cidade")
    private String nome;
    @Schema(description="estado da cidade", maxLength = 70, example = "SAO PAULO")
    @Column(name = "end_estado")
    private String estado;
    @Schema(description="sigla do estado da cidade", maxLength = 2, example = "SP")
    @Column(name = "end_uf")
    private String uf;
}
