package digytal.desktop.app.model.consulta.filtro;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import digytal.desktop.app.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import digytal.desktop.app.model.modulo.comum.MeioPagamento;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LancamentoFiltro {
	@JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate dataInicial;
	@JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataFinal;
    private Integer cadastro;
    private MeioPagamento meioPagamento;
    private AplicacaoTipo tipo;
}
