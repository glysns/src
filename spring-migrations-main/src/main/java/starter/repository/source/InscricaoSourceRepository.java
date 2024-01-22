package starter.repository.source;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import starter.model.source.InscricaoAlunoSourceEntity;
import starter.model.source.InscricaoSourceEntity;

import java.util.List;

@Repository
public interface InscricaoSourceRepository extends JpaRepository<InscricaoSourceEntity, String> {
    public List<InscricaoSourceEntity> findByIdGreaterThan(Integer id);
}