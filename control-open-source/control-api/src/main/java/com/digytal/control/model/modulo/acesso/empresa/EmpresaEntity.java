package com.digytal.control.model.modulo.acesso.empresa;


import com.digytal.control.model.comum.EntidadeCadastral;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "apl_acesso", name = "tab_empresa")
@Data
public class EmpresaEntity extends EntidadeCadastral {
    //private String localiza;
    private EmpresaIntegracao integracao = new EmpresaIntegracao();
}
