package starter.repository.source;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import starter.model.source.CadastroSourceEntity;
import starter.model.source.InscricaoSourceEntity;

import java.util.List;

@Repository
public interface CadastroSourceRepository extends JpaRepository<CadastroSourceEntity, String> {
    public List<CadastroSourceEntity> findByIdGreaterThan(Integer id);
}