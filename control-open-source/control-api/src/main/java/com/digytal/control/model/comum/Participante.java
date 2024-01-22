package com.digytal.control.model.comum;


import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class Participante {
    @Column(name = "part_empresa_id")
    private Integer empresa;
    @Column(name = "part_cadastro_id")
    private Integer cadastro;
    @Column(name = "part_organizacao_id")
    private Integer organizacao;
    @Column(name = "part_usuario_id")
    private Integer usuario;
}
