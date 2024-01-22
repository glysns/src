package com.digytal.control.model.modulo.cadastro;

import com.digytal.control.model.comum.EntidadeCadastral;
import com.digytal.control.model.comum.cadastramento.CadastroIntegracao;
import com.digytal.control.model.comum.cadastramento.CadastroPerfil;
import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "apl_cadastro", name = "tab_cadastro")
@Data
public class CadastroEntity extends EntidadeCadastral {
    @Embedded
    private CadastroPerfil perfil = new CadastroPerfil();
    @Embedded
    private CadastroIntegracao integracao;
    private String localiza;
    public CadastroEntity(){

    }
    public CadastroEntity(Integer organizacao){
        this.organizacao = organizacao;
    }
}
