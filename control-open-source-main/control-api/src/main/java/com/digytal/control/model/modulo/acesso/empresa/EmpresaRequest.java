package com.digytal.control.model.modulo.acesso.empresa;

import com.digytal.control.model.comum.cadastramento.CadastroCompletoRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Requisição da empresa", description = "Pré requisitos para empresa")
public class EmpresaRequest extends CadastroCompletoRequest {

}
