package com.digytal.control.model.consulta.lancamento;

import com.digytal.control.model.comum.filtro.FiltroData;
import com.digytal.control.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import com.digytal.control.model.comum.MeioPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Filtro de lançamentos", description = "Filtrar laçamentos da forlha de pagamento")
public class LancamentoFiltro extends FiltroData {
    @Schema(description = "identificador único do cadastro",type = "numeric", example= "2",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer cadastro;
    @Schema(description = "forma de pagamento", subTypes = MeioPagamento.class,requiredMode = Schema.RequiredMode.REQUIRED)
    private MeioPagamento meioPagamento;
    @Schema(description = "tipo de pagamento", subTypes = AplicacaoTipo.class,requiredMode = Schema.RequiredMode.REQUIRED)
    private AplicacaoTipo tipo;

}
