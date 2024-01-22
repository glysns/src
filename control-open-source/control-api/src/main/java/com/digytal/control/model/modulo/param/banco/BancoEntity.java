package com.digytal.control.model.modulo.param.banco;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "apl_param", name = "tab_banco")
@Data
public class BancoEntity {
    @Id
    private Integer id;
    @Schema(description = "compe",requiredMode = Schema.RequiredMode.REQUIRED, type = "numeric")
    private Integer compe;
    @Schema(description = "ISPB ",requiredMode = Schema.RequiredMode.REQUIRED, type = "numeric")
    private Long ispb;
    @Schema(description = "nome",requiredMode = Schema.RequiredMode.REQUIRED,type = "character")
    private String nome;
    @Column(name = "apelido")
    @Schema(description = "apelido",requiredMode = Schema.RequiredMode.REQUIRED,type = "character")
    private String legenda;
    //private String localiza;
}
