package com.digytal.control.model.modulo.financeiro.transacao;

import com.digytal.control.model.comum.Associacao;
import com.digytal.control.model.comum.RegistroData;
import com.digytal.control.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TransacaoResponse extends Transacao{
   private Integer id;
   private TransacaoValor valor;
   private AplicacaoTipo tipo;
   private RegistroData data;
   private Associacao cadastro;
   private Associacao area;
   private Associacao natureza;
}
