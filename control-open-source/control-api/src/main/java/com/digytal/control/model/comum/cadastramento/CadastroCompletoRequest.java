package com.digytal.control.model.comum.cadastramento;

import com.digytal.control.model.comum.endereco.Endereco;
import com.digytal.control.model.comum.telefone.Telefone;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(name = "Requisição do cadastro ",description = " Pré requisito para cadastro")
public class CadastroCompletoRequest extends CadastroSimplificadoRequest {
    @Schema(description = "telefone",subTypes = Telefone.class)
    private Telefone telefone = new Telefone();
    @Schema(description = "CPF ou CNPJ",minLength = 11,maxLength = 14,example = "58777339000189")
    private String cpfCnpj;
    @Schema(description = "RG (Registro Geral) ou LE (Inscrição Estadual)",minLength = 7,maxLength = 9,example = "15463342")
    private String rgIe;
    @Schema(description="data de nascimento ou constituição da empresa",type = "Date",example = "1984-06-30")
    private LocalDate aniversario;
    @Schema(description="atividade comercial ou profissional", example = "VENDA DE PRODUTOS IMPORTADOS")
    private String atividadeComecialProfissional;
    @Schema(description = "endereço",subTypes = Endereco.class)
    private Endereco endereco;
}
