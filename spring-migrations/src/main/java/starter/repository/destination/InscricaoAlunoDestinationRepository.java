package starter.repository.destination;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import starter.model.destination.InscricaoAlunoDestinationEntity;
import starter.model.source.InscricaoAlunoSourceEntity;

@Repository
public interface InscricaoAlunoDestinationRepository extends JpaRepository<InscricaoAlunoDestinationEntity, String> {
}