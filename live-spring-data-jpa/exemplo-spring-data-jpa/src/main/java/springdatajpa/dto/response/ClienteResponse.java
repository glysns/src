package springdatajpa.dto.response;

import java.time.LocalDate;

public interface ClienteResponse {
    Integer getId();
    String getNome();
    LocalDate getDataNascimento();
    EnderecoResponse getEndereco();
    ProfissaoResponse getProfissao();
}
