package digytal.backend.api.repository;

import digytal.backend.api.model.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    UsuarioEntity findByLogin(String login);
}
