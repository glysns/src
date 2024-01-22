package springdatajpawebapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import springdatajpawebapi.dto.response.ClienteResponse;
import springdatajpawebapi.dto.response.ProfissaoResponse;
import springdatajpawebapi.model.Cliente;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> , JpaSpecificationExecutor<Cliente> {
    @Query("SELECT c FROM Cliente c WHERE id = ?1")
    //@EntityGraph(attributePaths={"profissao"})
    //@EntityGraph(value = "cliente-full", type = EntityGraph.EntityGraphType.LOAD)
    @EntityGraph(attributePaths={"profissao","emails","telefones"})
    Cliente getFull(Integer id);

    List<ClienteResponse> findByNomeContaining(String nome);

    /**
     Quando extender de JpaSpecificationExecutor teremos novos findAll
     */

}
