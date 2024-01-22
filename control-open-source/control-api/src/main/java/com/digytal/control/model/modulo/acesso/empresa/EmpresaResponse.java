package com.digytal.control.model.modulo.acesso.empresa;


import com.digytal.control.model.comum.EntidadeCadastralResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Resposta referente a empresa")
public class EmpresaResponse extends EntidadeCadastralResponse {
}
