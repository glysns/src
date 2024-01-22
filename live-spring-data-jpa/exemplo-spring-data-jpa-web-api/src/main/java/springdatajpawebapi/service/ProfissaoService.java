package springdatajpawebapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import springdatajpawebapi.dto.response.ProfissaoResponse;
import springdatajpawebapi.model.Profissao;
import springdatajpawebapi.repository.ProfissaoRepository;

@Service
public class ProfissaoService {
    @Autowired
    private ProfissaoRepository repository;
    //PrePersist
    public void save(Profissao request){
        //deveria ser um dto
        repository.save(request);
    }
    //PreUpdate
    public void update(Integer id, Profissao request){
        //deveria ser um dto e tratar algumas regras
        Profissao entity = repository.findById(id).orElse(null);
        //existem outras maneiras de implementar isso
        entity.setNome(request.getNome());
        repository.save(entity);
    }
    //paginação
    public Page<ProfissaoResponse> listar(String nome, int page, int size){
        Pageable paginacao = PageRequest.of(page, size);
        Page<ProfissaoResponse> result=null;
        if(nome!=null && (!nome.isEmpty()))
            result = repository.findByNomeContaining(nome,paginacao);
        else
            result =repository.findBy(paginacao);
        return result;
    }
}
