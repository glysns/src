package digytal.desktop.app.model.modulo.comum;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import digytal.desktop.app.model.modulo.comum.endereco.Endereco;
import digytal.desktop.app.model.modulo.comum.telefone.Telefone;
import lombok.Data;

import java.time.LocalDate;

@Data
public abstract class EntidadeCadastralResponse {
    private Integer id;
    private String nomeFantasia;
    private String sobrenomeSocial;
    private String rgIe;
    private String cpfCnpj;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate aniversario;
    private String atividadeComecialProfissional;
    private Integer organizacao;
    private boolean incompleto;
    private Endereco endereco = new Endereco();
    private Telefone telefone = new Telefone();
}
