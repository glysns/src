package digytal.desktop.app.model.modulo.cadastro;

import digytal.desktop.app.model.modulo.comum.EntidadeCadastralResponse;
import digytal.desktop.app.model.modulo.comum.cadastramento.CadastroPerfil;
import lombok.Data;

@Data
public class CadastroResponse extends EntidadeCadastralResponse {
    private CadastroPerfil perfil;
}
