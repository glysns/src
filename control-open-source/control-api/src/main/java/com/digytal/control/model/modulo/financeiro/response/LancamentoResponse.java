package com.digytal.control.model.modulo.financeiro.response;

import com.digytal.control.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import com.digytal.control.model.comum.MeioPagamento;
import com.digytal.control.model.comum.RegistroData;
import com.digytal.control.model.comum.Associacao;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
@Schema(name = "Resposta do lançamento",description = "Resposta de lancamento")
public class LancamentoResponse {
    @Schema(description = "Identificador gerado pelo sistema")
    private Integer id;
    @Schema(description = "Número do documento informado manualmente gerado pelo sistema")
    private String numeroDocumento;
    private String titulo;
    private String descricao;
    private AplicacaoTipo tipo;
    private RegistroData data;
    private MeioPagamento meioPagamento;
    private String observacao;
    private Associacao area;
    private Associacao natureza;
    private String numeroTransacao;
    @Schema(name = "dados do cadastro",subTypes = Associacao.class)
    private Associacao cadastro;
}
