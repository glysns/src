package starter.repository.destination;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import starter.model.destination.InscricaoDestinationEntity;

@Repository
public interface InscricaoDestinationRepository extends JpaRepository<InscricaoDestinationEntity, String> {
}