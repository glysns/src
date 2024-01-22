package com.digytal.control.model.modulo.acesso.empresa.conta;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Resposta referente a conta")
public class ContaResponse extends ContaRequest {
   @Schema(description= "identificado único da conta")
   private Integer id;
   @Schema(description= "identificado único da empresa")
   private Integer empresa;
   @Schema(description= "saldo da conta")
   private Double saldo;
}
