package springdatajpawebapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import springdatajpawebapi.dto.response.ProfissaoResponse;
import springdatajpawebapi.model.Profissao;

public interface ProfissaoRepository extends JpaRepository<Profissao,Integer> {
    Page<ProfissaoResponse> findByNomeContaining(String nome, Pageable pageable);
    Page<ProfissaoResponse> findBy(Pageable pageable);
}
