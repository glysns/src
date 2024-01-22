package com.digytal.control.repository.modulo.acesso;

import com.digytal.control.model.modulo.acesso.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    boolean existsByLogin(String login);
    UsuarioEntity findByLogin(String login);
    /*




    UsuarioEntity findByCadastro(Integer cadastro);

    UsuarioEntity findByLoginOrEmail(String login,String email);

    @Query(value = "SELECT CASE WHEN COUNT(1) > 0 THEN 'true' ELSE 'false' END CONTAGEM FROM  apl_acessos.tab_usuario_empresa e WHERE e.usuario_id = ? AND e.empresa_id = ? ", nativeQuery = true)
    boolean hasEmpresa(Integer usuario, Integer empresa);

    @Query(value = "select CASE WHEN COUNT(1) > 0 THEN 'true' ELSE 'false' END CONTAGEM from apl_acessos.tab_usuario_empresa ue inner join apl_acessos.tab_empresa e " +
            "on ue.empresa_id = e.id inner join apl_acessos.tab_organizacao o on e.organizacao_id = o.id where ue.usuario_id = ? and o.id = ?", nativeQuery = true)
    boolean hasAccessOrganizacao(Integer usuario, Integer organizacao);

     */
}
