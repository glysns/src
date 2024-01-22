package digytal.desktop.app.model.modulo.cadastro;

import digytal.desktop.app.model.modulo.comum.cadastramento.CadastroCompletoRequest;
import digytal.desktop.app.model.modulo.comum.cadastramento.CadastroPerfil;
import lombok.Data;

@Data
public class CadastroRequest extends CadastroCompletoRequest {
    private CadastroPerfil perfil = new CadastroPerfil();
}
