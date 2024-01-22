package com.digytal.control.model.modulo.cadastro;

import com.digytal.control.model.comum.cadastramento.CadastroCompletoRequest;
import com.digytal.control.model.comum.cadastramento.CadastroPerfil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Requisição de cadastro",description = "Pré requisitos referente ao cadastro do perfil")
public class CadastroRequest extends CadastroCompletoRequest {
    @Schema(description = "perfil do cadastro",subTypes = CadastroPerfil.class)
    private CadastroPerfil perfil = new CadastroPerfil();
}
