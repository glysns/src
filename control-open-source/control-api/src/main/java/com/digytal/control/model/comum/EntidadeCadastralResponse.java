package com.digytal.control.model.comum;


import com.digytal.control.model.comum.endereco.Endereco;
import com.digytal.control.model.comum.telefone.Telefone;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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
