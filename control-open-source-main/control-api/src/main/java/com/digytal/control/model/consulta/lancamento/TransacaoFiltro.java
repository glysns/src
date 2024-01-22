package com.digytal.control.model.consulta.lancamento;

import com.digytal.control.model.comum.filtro.FiltroData;
import com.digytal.control.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Filtro de transações", description = "Parâmetros dos filtros para pesquisa de transações")
public class TransacaoFiltro extends FiltroData {
    @Schema(description = "identificador único do cadastro",type = "numeric", example= "2")
    private Integer cadastro;
    @Schema(description = "tipo de pagamento", subTypes = AplicacaoTipo.class)
    private AplicacaoTipo tipo;

}
