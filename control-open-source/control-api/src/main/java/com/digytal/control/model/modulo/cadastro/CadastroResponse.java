package com.digytal.control.model.modulo.cadastro;
import com.digytal.control.model.comum.EntidadeCadastralResponse;
import com.digytal.control.model.comum.cadastramento.CadastroPerfil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Resposta referente cadastro")
public class CadastroResponse extends EntidadeCadastralResponse {
    @Schema(description = "perfil do cadastro",subTypes = CadastroPerfil.class)
    private CadastroPerfil perfil;
}
