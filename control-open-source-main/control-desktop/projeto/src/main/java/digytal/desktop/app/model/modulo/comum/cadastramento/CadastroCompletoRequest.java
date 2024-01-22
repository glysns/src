package digytal.desktop.app.model.modulo.comum.cadastramento;

import lombok.Data;
import java.time.LocalDate;

import digytal.desktop.app.model.modulo.comum.endereco.Endereco;
import digytal.desktop.app.model.modulo.comum.telefone.Telefone;

@Data
public class CadastroCompletoRequest extends CadastroSimplificadoRequest {
    private Integer organizacao;
    private Telefone telefone = new Telefone();
    private String cpfCnpj;
    private String rgIe;
    private LocalDate aniversario;
    private String atividadeComecialProfissional;
    private Endereco endereco;
}
