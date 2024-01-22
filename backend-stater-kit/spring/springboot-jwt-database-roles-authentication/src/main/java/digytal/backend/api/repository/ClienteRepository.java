package digytal.backend.api.repository;

import digytal.backend.api.model.cliente.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {
}
