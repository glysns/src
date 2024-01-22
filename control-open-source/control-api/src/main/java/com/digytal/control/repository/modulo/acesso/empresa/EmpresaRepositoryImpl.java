package com.digytal.control.repository.modulo.acesso.empresa;

import com.digytal.control.infra.business.RegistroNaoLocalizadoException;
import com.digytal.control.infra.commons.validation.Entities;
import com.digytal.control.infra.model.usuario.EmpresaSimplificadaResponse;
import com.digytal.control.infra.persistence.QueryRepository;
import com.digytal.control.infra.sql.StringSQL;
import com.digytal.control.model.modulo.acesso.usuario.UsuarioEntity;
import com.digytal.control.repository.modulo.acesso.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.digytal.control.infra.commons.validation.Attributes.ID;

@Repository
public class EmpresaRepositoryImpl extends QueryRepository {
    @Autowired
    private UsuarioRepository usuarioRepository;
    public List<EmpresaSimplificadaResponse> listarEmpresas(Integer usuario){
        UsuarioEntity usuarioEntity = usuarioRepository.findById(usuario).orElseThrow(()-> new RegistroNaoLocalizadoException(Entities.USUARIO_ENTITY,ID));

        StringBuilder select = new StringBuilder();
        select.append(" SELECT e.id as id, e.cpfCnpj as cpfCnpj, e.nomeFantasia as nomeFantasia, e.sobrenomeSocial as sobrenomeSocial," +
                " e.organizacao as organizacao, ue.padrao as padrao" +
                " FROM EmpresaEntity e JOIN UsuarioEmpresaEntity ue ON e.id = ue.empresa and ue.usuario = " + usuario + " ORDER BY ue.padrao DESC");
        StringSQL sql = new StringSQL();
        sql.select(select.toString());

        return search(sql, EmpresaSimplificadaResponse.class);

    }
}