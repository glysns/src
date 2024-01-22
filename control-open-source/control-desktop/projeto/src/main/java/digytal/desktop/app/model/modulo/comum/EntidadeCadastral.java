package digytal.desktop.app.model.modulo.comum;

import java.time.LocalDate;

import digytal.desktop.app.model.modulo.comum.endereco.Endereco;
import digytal.desktop.app.model.modulo.comum.telefone.Telefone;
import lombok.Data;

@Data
public class EntidadeCadastral {
    private Integer id;
    private String nomeFantasia;
    private String sobrenomeSocial;
    private String rgIe;
    private String cpfCnpj;
    private String email;
    private Endereco endereco = new Endereco();
    private Telefone telefone = new Telefone();
    private LocalDate aniversario;
    private String atividadeComecialProfissional="";
    private Integer organizacao;
    private boolean incompleto;
}
