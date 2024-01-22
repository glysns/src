package digytal.backend.api.service;

import digytal.backend.api.infra.handler.exceptions.CampoObrigatorioException;
import digytal.backend.api.infra.handler.exceptions.RegistroNaoLocalizadoException;
import digytal.backend.api.model.cliente.ClienteEntity;
import digytal.backend.api.model.cliente.ClienteRequest;
import digytal.backend.api.model.cliente.ClienteResponse;
import digytal.backend.api.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    //PrePersist
    public Integer save(ClienteRequest request){
        //chamando o método da propria classe para cuidar em persistir no banco de dados
        return this.persist(null, request);
    }
    //PreUpdate
    public Integer update(Integer id, ClienteRequest request){
        //chamando o método da propria classe para cuidar em persistir no banco de dados
        return this.persist(id, request);
    }
    public boolean delete(Integer id){
       ClienteEntity entity = findById(id);
       repository.delete(entity);
       return true;
    }
    public ClienteResponse getOne(Integer id){
        ClienteEntity entity = findById(id);
        ClienteResponse response = new ClienteResponse();
        //este método copia todos os atributos da entity para o response;
        BeanUtils.copyProperties(entity,response);
        return response;
    }
    public List<ClienteResponse> listAll(){
        List<ClienteEntity> entitys = repository.findAll();
        List<ClienteResponse> list = new ArrayList<>();

        for(ClienteEntity entity:entitys){
            ClienteResponse response = new ClienteResponse();
            BeanUtils.copyProperties(entity,response);
            list.add(response);
        }

        return list;
    }
    private ClienteEntity findById(Integer id){
        return repository.findById(id).orElseThrow(() -> new RegistroNaoLocalizadoException("Cliente", "ID"));
    }

    @Transactional
    public Integer persist(Integer id, ClienteRequest request){

        if(request.getNome()==null || request.getNome().isEmpty())
            throw new CampoObrigatorioException("Nome");

        if(request.getCpf()==null || request.getCpf().isEmpty())
            throw new CampoObrigatorioException("CPF");

        ClienteEntity entity = null;
        if(id!=null){
            entity = this.findById(id); //este noo método tem o mesmo do repositório mas observe a lógica aplicada
        }else
            entity = new ClienteEntity();

        //este método copia todos os atributos do request para o entity
        BeanUtils.copyProperties(request,entity);

        return repository.save(entity).getId();
    }
}
