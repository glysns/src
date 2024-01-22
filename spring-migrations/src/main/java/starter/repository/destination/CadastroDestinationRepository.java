package starter.repository.destination;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import starter.model.destination.CadastroDestinationEntity;
import starter.model.source.CadastroSourceEntity;

@Repository
public interface CadastroDestinationRepository extends JpaRepository<CadastroDestinationEntity, String> {
}